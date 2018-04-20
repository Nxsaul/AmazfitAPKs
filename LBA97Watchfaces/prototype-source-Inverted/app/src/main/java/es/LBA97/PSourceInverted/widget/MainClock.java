package es.LBA97.PSourceInverted.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
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
import com.ingenic.iwds.slpt.view.digital.SlptWeekView;
import com.ingenic.iwds.slpt.view.digital.SlptYear0View;
import com.ingenic.iwds.slpt.view.digital.SlptYear1View;
import com.ingenic.iwds.slpt.view.digital.SlptYear2View;
import com.ingenic.iwds.slpt.view.digital.SlptYear3View;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import es.LBA97.PSourceInverted.R;
import es.LBA97.PSourceInverted.resource.ResourceManager;

/**
 * Clock
 */
public class MainClock extends DigitalClockWidget {

    private TextPaint hourFont;
    private TextPaint timeFont;
    private TextPaint dateFont;
    private TextPaint weekdayFont;

    private Drawable background;
    private float leftHour;
    private float topHour;
    private float leftMinute;
    private float topMinute;
    private float leftDate;
    private float topDate;
    private float leftWeekday;
    private float topWeekday;

    private String[] digitalNums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    @Override
    public void init(Service service) {

        this.background = service.getResources().getDrawable(R.drawable.background);
        this.background.setBounds(0, 0, 320, 320);

        this.leftHour = service.getResources().getDimension(R.dimen.time_hour_left);
        this.topHour = service.getResources().getDimension(R.dimen.time_hour_top);
        this.leftMinute = service.getResources().getDimension(R.dimen.time_minute_left);
        this.topMinute = service.getResources().getDimension(R.dimen.time_minute_top);
        this.leftDate = service.getResources().getDimension(R.dimen.date_left);
        this.topDate = service.getResources().getDimension(R.dimen.date_top);
        this.leftWeekday = service.getResources().getDimension(R.dimen.weekday_left);
        this.topWeekday = service.getResources().getDimension(R.dimen.weekday_top);


        this.hourFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.hourFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE));
        this.hourFont.setTextSize(service.getResources().getDimension(R.dimen.time_font_size));
        this.hourFont.setColor(service.getResources().getColor(R.color.time_colour));
        this.hourFont.setTextAlign(Paint.Align.CENTER);

        this.timeFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.timeFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE));
        this.timeFont.setTextSize(service.getResources().getDimension(R.dimen.time_font_size));
        this.timeFont.setColor(service.getResources().getColor(R.color.hour_colour));
        this.timeFont.setTextAlign(Paint.Align.CENTER);

        this.dateFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.dateFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE));
        this.dateFont.setTextSize(service.getResources().getDimension(R.dimen.date_font_size));
        this.dateFont.setColor(service.getResources().getColor(R.color.time_colour));
        this.dateFont.setTextAlign(Paint.Align.CENTER);

        this.weekdayFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.weekdayFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.IRRESISTIBLE_HOLLYWOOD));
        this.weekdayFont.setTextSize(service.getResources().getDimension(R.dimen.weekday_font_size));
        this.weekdayFont.setColor(service.getResources().getColor(R.color.time_colour));
        this.weekdayFont.setTextAlign(Paint.Align.CENTER);

    }


    @Override
    public void onDrawDigital(Canvas canvas, float width, float height, float centerX, float centerY, int seconds, int minutes, int hours, int year, int month, int day, int week, int ampm) {

        this.background.draw(canvas);

        canvas.drawText(Util.formatTime(hours), this.leftHour, this.topHour, this.hourFont);

        canvas.drawText(Util.formatTime(minutes), this.leftMinute, this.topMinute, this.timeFont);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, week);
        String date = String.format("%02d.%02d.%02d", day, month, year);
        canvas.drawText(date, leftDate, topDate, this.dateFont);

        String weekday = String.format("%S", new SimpleDateFormat("EE").format(calendar.getTime()));
        canvas.drawText(weekday, leftWeekday, topWeekday, this.weekdayFont);

        int nSecond = calendar.get(13);
        String segundero = String.format(":");
        if(nSecond%2 ==0 ) {
            canvas.drawText(segundero, 160, 190, this.hourFont);
        }

    }


    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        SlptPictureView background = new SlptPictureView();
        background.setImagePicture(Util.assetToBytes(service, "background_splt.png"));

        Typeface timeTypeFace = ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE);

        SlptLinearLayout hourLayout = new SlptLinearLayout();
        hourLayout.add(new SlptHourHView());
        hourLayout.add(new SlptHourLView());
        hourLayout.setStringPictureArrayForAll(this.digitalNums);
        hourLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.time_font_size),
                -16777216 ,
                timeTypeFace
        );
        hourLayout.setStart(
                35,121
        );
        hourLayout.alignX = (byte) 2;
        hourLayout.alignY = (byte) 0;
        hourLayout.setRect(110, 120);

        SlptLinearLayout minuteLayout = new SlptLinearLayout();
        minuteLayout.add(new SlptMinuteHView());
        minuteLayout.add(new SlptMinuteLView());
        minuteLayout.setStringPictureArrayForAll(this.digitalNums);
        minuteLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.time_font_size),
                -16777216 ,
                timeTypeFace
        );
        minuteLayout.setStart(
                180,121
        );
        minuteLayout.alignX = (byte) 2;
        minuteLayout.alignY = (byte) 0;
        minuteLayout.setRect(110, 120);
/*
        SlptLinearLayout timeLayout = new SlptLinearLayout();
        SlptPictureView space = new SlptPictureView();
        space.setStringPicture(" ");
        space.setStringPicture("\u0020 \u0020");
        timeLayout.add(new SlptHourHView());
        timeLayout.add(new SlptHourLView());
        timeLayout.add(space);
        timeLayout.add(new SlptMinuteHView());
        timeLayout.add(new SlptMinuteLView());
        timeLayout.setStringPictureArrayForAll(this.digitalNums);
        timeLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.time_font_size),
                -16777216 ,
                timeTypeFace
        );
        timeLayout.setStart(
                0,121
        );
        timeLayout.alignX = (byte) 2;
        timeLayout.alignY = (byte) 0;
        timeLayout.setRect(320, 120);
*/

        SlptLinearLayout secondsLayout = new SlptLinearLayout();
        SlptPictureView colon = new SlptPictureView();
        colon.setStringPicture(":");
        secondsLayout.add(colon);
        secondsLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.time_font_size),
                -16777216 ,
                timeTypeFace
        );
        secondsLayout.setStart(
                148,120
        );

        SlptPictureView point = new SlptPictureView();
        point.setStringPicture(".");


        SlptLinearLayout dayLayout = new SlptLinearLayout();
        dayLayout.add(new SlptDayHView());
        dayLayout.add(new SlptDayLView());
        dayLayout.add(point);
        dayLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.date_font_size),
                -16777216,
                timeTypeFace);
        dayLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.date_left_slpt),
                (int) service.getResources().getDimension(R.dimen.date_top_slpt));


        SlptLinearLayout monthLayout = new SlptLinearLayout();
        monthLayout.add(new SlptMonthHView());
        monthLayout.add(new SlptMonthLView());
        monthLayout.add(point);
        monthLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.date_font_size),
                -16777216,
                timeTypeFace);
        monthLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.month_left_slpt),
                (int) service.getResources().getDimension(R.dimen.month_top_slpt));

        SlptLinearLayout yearLayout = new SlptLinearLayout();
        yearLayout.add(new SlptYear3View());
        yearLayout.add(new SlptYear2View());
        yearLayout.add(new SlptYear1View());
        yearLayout.add(new SlptYear0View());
        yearLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.date_font_size),
                -16777216,
                timeTypeFace
        );
        yearLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.year_left_slpt),
                (int) service.getResources().getDimension(R.dimen.year_top_slpt)
        );

        Typeface weekfont = ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.IRRESISTIBLE_HOLLYWOOD);


        SlptLinearLayout WeekdayLayout = new SlptLinearLayout();
        WeekdayLayout.add(new SlptWeekView());
        WeekdayLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.week_left_slpt),
                (int) service.getResources().getDimension(R.dimen.week_top_slpt));
        WeekdayLayout.setTextAttrForAll(service.getResources().getDimension(R.dimen.weekday_font_size),
                -16777216,
                weekfont
        );
        WeekdayLayout.alignX = (byte) 2;
        WeekdayLayout.alignY = (byte) 0;
        WeekdayLayout.setRect(50, 40);


        return Arrays.asList(background, hourLayout, minuteLayout, secondsLayout, dayLayout, monthLayout, WeekdayLayout, yearLayout);
    }
}
