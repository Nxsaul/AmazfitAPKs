package it.drbourbon.mywatchfaces.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;

//import com.huami.watch.watchface.AnalogWatchFaceEleven;
//import com.huami.watch.watchface.DigitalWatchFaceSportElevenSlpt;
//import com.huami.watch.watchface.DigitalWatchFaceSportFive;
//import com.huami.watch.watchface.DigitalWatchFaceSportFiveSlpt;
import com.huami.watch.watchface.DigitalWatchFaceSportOneSlpt;
import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.digital.SlptHourHView;
import com.ingenic.iwds.slpt.view.digital.SlptHourLView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteHView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteLView;
import com.ingenic.iwds.slpt.view.digital.SlptTimeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.ravenliquid.watchfaces.R;
import es.malvarez.mywatchfaces.resource.ResourceManager;
import es.malvarez.mywatchfaces.widget.DigitalClockWidget;

/**
 * Clock
 */
public class FuzzyTextClock extends DigitalClockWidget {

    private TextPaint textFont;

    private int background;
    private float leftFuzzyText;
    private float fontHeight;

    private String hours[];
    private String phrases[];
    private String digits[];

    private String getPhrase(int minutes) {
        int roundedMinutes = 5*(Math.round((float)minutes / 5.0f));
        int pos = (roundedMinutes / 5) % 12;
        return phrases[pos];
    }
    private String hourWord(int h) {
        int pos = (h + 11)%12;
        return hours[pos];
    }
    private String timeToWord(int hours, int minutes) {
        String phrase = getPhrase(minutes);
        String fuzzy = phrase.replace("*$1", hourWord(hours));
        String fuzzy2 = fuzzy.replace("*$2", hourWord(hours+1));
        return fuzzy2;
    }

    /*
    private static List<String> splitString(String str, int chunksize) {
        char[] chars = str.toCharArray();
        ArrayList<String> list = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for(char character : chars) {
            if(count < chunksize - 1) {
                builder.append(character);
                count++;
            }
            else {
                if(character == ' ') {
                    builder.append(character);
                    list.add(builder.toString());
                    count = 0;
                    builder.setLength(0);
                }
                else {
                    builder.append(character);
                    count++;
                }
            }
        }
        list.add(builder.toString());
        builder.setLength(0);
        return list;
    }
    */

    @Override
    public void init(Service service) {

        this.background = service.getResources().getColor(R.color.malvarez_background);
        this.leftFuzzyText = 160;

        this.textFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.textFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE));
        this.textFont.setTextSize(service.getResources().getDimension(R.dimen.drbourbon_fuzzytext_font_size));
        this.textFont.setColor(service.getResources().getColor(R.color.malvarez_time_colour));
        this.textFont.setTextAlign(Paint.Align.CENTER);

        hours = service.getResources().getStringArray(R.array.hours);
        phrases = service.getResources().getStringArray(R.array.phrases);
        digits = service.getResources().getStringArray(R.array.digits);

        Rect bounds = new Rect();
        String sampleTime = "12:58";
        textFont.getTextBounds(sampleTime, 0, sampleTime.length(), bounds);

        this.fontHeight = bounds.height();

    }

    @Override
    public void onDrawDigital(Canvas canvas, float width, float height, float centerX, float centerY, int seconds, int minutes, int hours, int year, int month, int day, int week, int ampm) {
        canvas.drawColor(background);

        String sFuzzyNow = timeToWord(hours, minutes);
        /*
        Calendar rightNow = Calendar.getInstance();
        String sFuzzyNow = timeToWord(rightNow.get(Calendar.HOUR), rightNow.get(Calendar.MINUTE));
        */

        String sRows[] = sFuzzyNow.split(" ");//splitString(sFuzzyNow, 8);
        /* sFuzzyNow.split("(?<=\\G.{"+8+"})")*/

        float fheight = this.fontHeight + 4;//textFont.descent() - textFont.ascent();
        float totalHeight = fheight * (sRows.length-1);
        float x=leftFuzzyText;
        float y=  fheight/2 + height/2 - totalHeight/2;
        for (String line: sRows) {
            String rLine = line.replace("_", " ");
            canvas.drawText(rLine, x, y, textFont);
            y += fheight;
        }
//        canvas.drawText(sFuzzyNow, leftFuzzyText, topFuzzyText, textFont);
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        return null;
    }
}
