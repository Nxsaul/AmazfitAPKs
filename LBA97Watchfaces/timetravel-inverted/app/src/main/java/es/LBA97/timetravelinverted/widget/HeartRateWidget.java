package es.LBA97.timetravelinverted.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;

import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.sport.SlptLastHeartRateView;

import java.util.Collections;
import java.util.List;

import es.LBA97.timetravelinverted.R;
import es.LBA97.timetravelinverted.data.DataType;
import es.LBA97.timetravelinverted.data.HeartRate;
import es.LBA97.timetravelinverted.resource.ResourceManager;


public class HeartRateWidget extends AbstractWidget {

    private TextPaint textPaint;
    private HeartRate heartRate;

    private float textTop;
    private float textLeft;

    @Override
    public void init(Service service) {
        this.textLeft = service.getResources().getDimension(R.dimen.widget_text_left);
        this.textTop = service.getResources().getDimension(R.dimen.widget_text_top);

        this.textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.textPaint.setColor(service.getResources().getColor(R.color.time_colour));
        this.textPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE));
        this.textPaint.setTextSize(service.getResources().getDimension(R.dimen.circles_font_size));
        this.textPaint.setTextAlign(Paint.Align.CENTER);
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
        String text = heartRate == null || heartRate.getHeartRate() < 25 ? "--" : String.format("%d", heartRate.getHeartRate());
        canvas.drawText(text, textLeft, textTop, textPaint);
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        SlptLinearLayout heart = new SlptLinearLayout();
        heart.add(new SlptLastHeartRateView());
        heart.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.circles_font_size_slpt),
                -16777216,
                ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE)
        );
        heart.setStart(
                (int) service.getResources().getDimension(R.dimen.widget_text_left_slpt),
                (int) service.getResources().getDimension(R.dimen.widget_text_top_slpt));
        heart.alignX=2;
        heart.alignY=0;
        heart.setRect(60,60);
        return Collections.<SlptViewComponent>singletonList(heart);
    }
}
