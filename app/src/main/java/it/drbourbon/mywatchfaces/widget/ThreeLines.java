package it.drbourbon.mywatchfaces.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;

import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptLayout;
import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptNumView;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.digital.SlptDayHView;
import com.ingenic.iwds.slpt.view.digital.SlptDayLView;
import com.ingenic.iwds.slpt.view.digital.SlptHourHView;
import com.ingenic.iwds.slpt.view.digital.SlptHourLView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteHView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteLView;
import com.ingenic.iwds.slpt.view.digital.SlptMonthHView;
import com.ingenic.iwds.slpt.view.digital.SlptMonthLView;
import com.ingenic.iwds.slpt.view.predrawed.SlptPredrawedHourWithMinuteView;
import com.ingenic.iwds.slpt.view.utils.SimpleFile;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import es.malvarez.mywatchfaces.data.DataType;
import es.malvarez.mywatchfaces.resource.ResourceManager;
import es.malvarez.mywatchfaces.widget.DigitalClockWidget;

import com.ravenliquid.watchfaces.R;
import com.ravenliquid.watchfaces.Utility;

/**
 * Created by fabio on 23/05/17.
 */

public class ThreeLines extends DigitalClockWidget {
    private final String[] digitalNums = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private TextPaint timeFont;
    private TextPaint dateFont;
    private int background;
    private float verticalOffset;
    private float fontHeight;
    private final int nLines = 3;
    private float globalVerticalOffset;

    public ThreeLines(float verticalOffset) {
        this.globalVerticalOffset = verticalOffset;
    }

    @Override
    public void init(Service service) {

        this.background = service.getResources().getColor(R.color.threelines_background);
        this.verticalOffset = service.getResources().getDimension(R.dimen.threelines_voffset);

        this.timeFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.timeFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.OPEN24));
        this.timeFont.setTextSize(service.getResources().getDimension(R.dimen.threelines_font_size));
        this.timeFont.setColor(service.getResources().getColor(R.color.threelines_time_color));
        this.timeFont.setTextAlign(Paint.Align.CENTER);

        this.dateFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.dateFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.OPEN24));
        this.dateFont.setTextSize(service.getResources().getDimension(R.dimen.threelines_font_size));
        this.dateFont.setColor(service.getResources().getColor(R.color.threelines_date_color));
        this.dateFont.setTextAlign(Paint.Align.CENTER);

        Rect bounds = new Rect();
        String sampleTime = "12:58";
        timeFont.getTextBounds(sampleTime, 0, sampleTime.length(), bounds);

        this.fontHeight = bounds.height() + verticalOffset;
    }

    @Override
    public void onDrawDigital(Canvas canvas, float width, float height, float centerX, float centerY, int seconds, int minutes, int hours, int year, int month, int day, int week, int ampm) {
        canvas.drawColor(background);

        String sDate = String.format("%02d/%02d", day, month);
        String sTime = Util.formatTime(hours) + ":" + Util.formatTime(minutes);

        final float totalHeight = fontHeight * (nLines - 1) - verticalOffset;
        final float x = width / 2;
        float y = globalVerticalOffset + fontHeight / 2 + height / 2 - totalHeight / 2 - nLines*4 + 2;

        canvas.drawText(sDate, x, y, this.dateFont);
        y += fontHeight;
        canvas.drawText(sTime, x, y, this.timeFont);
    }

    private SlptViewComponent buildDateView(Service service, int x, int y) {
        SlptLinearLayout var21 = new SlptLinearLayout();
        SlptMonthHView var34 = new SlptMonthHView();
        SlptMonthLView var35 = new SlptMonthLView();
        SlptDayHView var32 = new SlptDayHView();
        SlptDayLView var33 = new SlptDayLView();
        SlptPictureView var27 =  new SlptPictureView();

        var27.setImagePicture(SimpleFile.readFileFromAssets(service.getApplicationContext(), "slash-62-red.png"));


        // linear: hh:mm
        var21.add(var32);
        var21.add(var33);
        var21.add(var27);
        var21.add(var34);
        var21.add(var35);

        var21.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.threelines_font_size),
                service.getResources().getColor(R.color.threelines_date_color),
                ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.OPEN24)
        );
        Utility.setStringPictureArrayForAll(var21, this.digitalNums);
        var21.setStart(x, y);
        var21.centerHorizontal = 1;
        var21.alignX = 2;
        var21.alignY = 1;

        return var21;
    }

    private SlptViewComponent buildTimeView(Service service, int x, int y) {
        SlptLinearLayout var21 = new SlptLinearLayout();
        SlptHourHView var32 = new SlptHourHView();
        SlptHourLView var33 = new SlptHourLView();
        SlptPictureView var27 =  new SlptPictureView();
        SlptMinuteHView var34 = new SlptMinuteHView();
        SlptMinuteLView var35 = new SlptMinuteLView();

        var27.setImagePicture(SimpleFile.readFileFromAssets(service.getApplicationContext(), "dots-62.png"));

        // linear: hh:mm
        var21.add(var32);
        var21.add(var33);
        var21.add(var27);
        var21.add(var34);
        var21.add(var35);

        var21.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.threelines_font_size),
                service.getResources().getColor(R.color.threelines_time_color),
                ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.OPEN24)
        );
        Utility.setStringPictureArrayForAll(var21, this.digitalNums);
        var21.setStart(x, y);
        var21.centerHorizontal = 1;
        var21.alignX = 2;
        var21.alignY = 1;

        return var21;
    }

    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        init(service);

        final float totalHeight = fontHeight * nLines - verticalOffset;
        final float x = 160;
        float y = 160 - totalHeight / 2 + 1;

        List<SlptViewComponent> retVal = new LinkedList<>();// ArrayList<>();

        SlptPictureView bg = new SlptPictureView();
        bg.setImagePicture(SimpleFile.readFileFromAssets(service.getApplicationContext(), "black.png"));
        retVal.add(bg);

        retVal.add(buildDateView(service, (int) x, (int) y));

        y += fontHeight;

        retVal.add(buildTimeView(service, (int) x, (int) y));

        return retVal;
    }
}
