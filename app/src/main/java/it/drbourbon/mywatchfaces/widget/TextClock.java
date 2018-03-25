package it.drbourbon.mywatchfaces.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;

import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.digital.SlptHourHView;
import com.ingenic.iwds.slpt.view.digital.SlptHourLView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteHView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteLView;
import com.ingenic.iwds.slpt.view.digital.SlptTimeView;
import com.ingenic.iwds.slpt.view.utils.SimpleFile;
import com.ravenliquid.watchfaces.R;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import es.malvarez.mywatchfaces.resource.ResourceManager;
import es.malvarez.mywatchfaces.widget.DigitalClockWidget;

/**
 * Clock
 */
public class TextClock extends DigitalClockWidget {

    private TextPaint textFont;

    private int background;
    private int textColor;
    private int textAltColor;
    private float leftFuzzyText;
    private float fontHeight;

    private String digits[];

    @Override
    public void init(Service service) {

        this.background = service.getResources().getColor(R.color.malvarez_background);
        this.textColor = service.getResources().getColor(R.color.malvarez_time_colour_slpt);
        this.textAltColor = service.getResources().getColor(R.color.malvarez_hour_colour_slpt);

        this.leftFuzzyText = 160;

        this.textFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.textFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE));
        this.textFont.setTextSize(service.getResources().getDimension(R.dimen.drbourbon_fuzzytext_font_size));
        this.textFont.setColor(service.getResources().getColor(R.color.malvarez_time_colour));
        this.textFont.setTextAlign(Paint.Align.CENTER);

        digits = service.getResources().getStringArray(R.array.digits);

        Rect bounds = new Rect();
        String sampleTime = "12:58";
        textFont.getTextBounds(sampleTime, 0, sampleTime.length(), bounds);

        this.fontHeight = bounds.height();
    }

    @Override
    public void onDrawDigital(Canvas canvas, float width, float height, float centerX, float centerY, int seconds, int minutes, int hours, int year, int month, int day, int week, int ampm) {
        canvas.drawColor(background);

        int digitCount = 4;
        final float fheight = fontHeight + 4;
        final float totalHeight = fheight * (digitCount - 1);
        float y = fheight/2 + height/2 - totalHeight/2 - 1;
        int i=0;
        for (int d: Arrays.asList( hours/10, hours%10, minutes/10, minutes%10 )) {
            String rLine = digits[d];
            textFont.setColor(i<2 ? textAltColor : textColor);
            canvas.drawText(rLine, leftFuzzyText, y, textFont);
            y += fheight;
            i++;
        }
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {

        init(service);

        float textSize = service.getResources().getDimension(R.dimen.drbourbon_fuzzytext_font_size);
        int textColor = service.getResources().getColor(R.color.malvarez_time_colour_slpt);
        int textAltColor = service.getResources().getColor(R.color.malvarez_hour_colour_slpt);
        Typeface typeFace = ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE);

        int digitCount = 4;
        List<SlptViewComponent> retVal = new LinkedList<>();// ArrayList<>();

        SlptPictureView bg = new SlptPictureView();
        bg.setImagePicture(SimpleFile.readFileFromAssets(service.getApplicationContext(), "black.png"));
        retVal.add(bg);

        float textHeight = fontHeight + 4;
        float totalHeight = textHeight * digitCount;
        float x = 160;
        float y = 160 - totalHeight/2 - digitCount*3;

        SlptHourHView hh = new SlptHourHView();
        SlptHourLView hl = new SlptHourLView();
        SlptMinuteHView mh = new SlptMinuteHView();
        SlptMinuteLView ml = new SlptMinuteLView();

        for (SlptTimeView c : Arrays.asList(hh, hl, mh, ml)){
            c.setStart(Math.round(x),Math.round(y));
            c.centerHorizontal = 1;
            c.setTextAttr(
                    textSize,
                    c==hh || c==hl ? textAltColor : textColor,
                    typeFace);
            c.setStringPictureArray(digits);
            y += textHeight;
            retVal.add(c);
        }


        for(SlptViewComponent c : retVal){
            Log.e("FUZZY", "Component " + c.id + ": x:" + c.x + ", y:" + c.y);
        }

        return retVal;
    }
}
