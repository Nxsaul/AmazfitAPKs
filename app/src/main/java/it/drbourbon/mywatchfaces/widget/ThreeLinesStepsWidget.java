package it.drbourbon.mywatchfaces.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.sport.SlptLastHeartRateView;
import com.ingenic.iwds.slpt.view.sport.SlptTodayStepNumView;

import java.util.Collections;
import java.util.List;

import es.malvarez.mywatchfaces.data.DataType;
import es.malvarez.mywatchfaces.data.HeartRate;
import es.malvarez.mywatchfaces.data.Steps;
import es.malvarez.mywatchfaces.resource.ResourceManager;
import es.malvarez.mywatchfaces.widget.AbstractWidget;
import com.ravenliquid.watchfaces.R;

/**
 * Heart rate widget
 */
public class ThreeLinesStepsWidget extends AbstractWidget {
    private TextPaint stepsFont;
    private Steps stepsData;
    private float verticalOffset;
    private float fontHeight;
    private final int nLines = 3;

    @Override
    public void init(Service service) {
        this.verticalOffset = service.getResources().getDimension(R.dimen.threelines_voffset);
        this.stepsFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.stepsFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.OPEN24));
        this.stepsFont.setTextSize(service.getResources().getDimension(R.dimen.threelines_font_size));
        this.stepsFont.setColor(service.getResources().getColor(R.color.threelines_steps_color));
        this.stepsFont.setTextAlign(Paint.Align.CENTER);

        Rect bounds = new Rect();
        String sampleTime = "12:58";
        stepsFont.getTextBounds(sampleTime, 0, sampleTime.length(), bounds);

        this.fontHeight = bounds.height() + verticalOffset;

    }

    @Override
    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.STEPS);
    }

    @Override
    public void onDataUpdate(DataType type, Object value) {
        this.stepsData = (Steps) value;
    }

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        String sSteps = stepsData == null ? "--" : String.format("%d", stepsData.getSteps());

        final float totalHeight = fontHeight * (nLines - 1) - verticalOffset;
        float x = width / 2;
        float y = fontHeight/2 + height/2 - totalHeight/2 - nLines*4;

        y += fontHeight*2;
        canvas.drawText(sSteps, x, y, this.stepsFont);
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        init(service);

        final float totalHeight = fontHeight * nLines - verticalOffset;
        final float x = 160;
        float y = 160 - totalHeight/2 ;

        SlptLinearLayout steps = new SlptLinearLayout();
        steps.add(new SlptTodayStepNumView());
        steps.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.threelines_font_size),
                service.getResources().getColor(R.color.threelines_steps_color),
                ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.OPEN24)
        );
        steps.centerHorizontal = 1;

        y += fontHeight*2;

        steps.setStart((int)x,(int)y);

        return Collections.<SlptViewComponent>singletonList(steps);
    }
}
