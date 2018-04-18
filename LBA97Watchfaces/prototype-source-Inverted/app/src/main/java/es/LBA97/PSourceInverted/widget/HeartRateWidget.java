package es.LBA97.PSourceInverted.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.sport.SlptLastHeartRateView;

import java.util.Collections;
import java.util.List;

import es.LBA97.PSourceInverted.R;
import es.LBA97.PSourceInverted.data.DataType;
import es.LBA97.PSourceInverted.data.HeartRate;
import es.LBA97.PSourceInverted.resource.ResourceManager;

/**
 * Heart rate widget
 */
public class HeartRateWidget extends AbstractWidget {

    private TextPaint textPaint;
    private HeartRate heartRate;

    private float textTop;
    private float textLeft;

    private Drawable heartIcon;

    @Override
    public void init(Service service) {
        this.textLeft = service.getResources().getDimension(R.dimen.heart_rate_text_left);
        this.textTop = service.getResources().getDimension(R.dimen.heart_rate_text_top);

        this.textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.textPaint.setColor(service.getResources().getColor(R.color.time_colour));
        this.textPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE));
        this.textPaint.setTextSize(service.getResources().getDimension(R.dimen.circles_font_size));
        this.textPaint.setTextAlign(Paint.Align.CENTER);

        this.heartIcon = service.getResources().getDrawable(R.drawable.heart, null);
        this.setDrawableBounds(this.heartIcon, service.getResources().getDimension(R.dimen.heart_rate_icon_left), service.getResources().getDimension(R.dimen.heart_rate_icon_top));
    }

    @Override
    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.HEART_RATE);
    }

    @Override
    public void onDataUpdate(DataType type, Object value) {
        this.heartRate = (HeartRate) value;
    }

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        String text = heartRate == null || heartRate.getHeartRate() < 25 ? "--" : String.format("%d bpm", heartRate.getHeartRate());
        canvas.drawText(text, textLeft, textTop, textPaint);
        //this.heartIcon.draw(canvas);
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        SlptLinearLayout heart = new SlptLinearLayout();
        SlptPictureView bpm = new SlptPictureView();
        bpm.setStringPicture(" bpm");
        heart.add(new SlptLastHeartRateView());
        heart.add(bpm);
        heart.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.circles_font_size_slpt),
                -16777216,
                ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE)
        );
        heart.setStart(
                (int) service.getResources().getDimension(R.dimen.heart_rate_text_left_slpt),
                (int) service.getResources().getDimension(R.dimen.heart_rate_text_top_slpt));
        heart.alignX= 2;
        heart.alignY= 0;
        heart.setRect(100, 30);
        return Collections.<SlptViewComponent>singletonList(heart);
    }
}
