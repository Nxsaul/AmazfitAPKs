package es.LBA97.timetravelinverted.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptLayout;
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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import es.LBA97.timetravelinverted.R;
import es.LBA97.timetravelinverted.resource.ResourceManager;

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
    private byte[][] week_num = new byte[7][];


    @Override
    public void init(Service service) {
        this.background = service.getResources().getDrawable(R.drawable.background_heart);
        this.background.setBounds(0,0,320,320);

        this.leftHour = service.getResources().getDimension(R.dimen.time_hour_left);
        this.topHour = service.getResources().getDimension(R.dimen.time_hour_top);
        this.leftMinute = service.getResources().getDimension(R.dimen.time_minute_left);
        this.topMinute = service.getResources().getDimension(R.dimen.time_minute_top);
        this.leftDate = service.getResources().getDimension(R.dimen.date_left);
        this.topDate = service.getResources().getDimension(R.dimen.date_top);

        this.leftWeekday = service.getResources().getDimension(R.dimen.weekday_left);
        this.topWeekday = service.getResources().getDimension(R.dimen.weekday_top);

        this.hourFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.hourFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE));
        this.hourFont.setTextSize(service.getResources().getDimension(R.dimen.time_font_size));
        this.hourFont.setColor(service.getResources().getColor(R.color.time_colour));
        this.hourFont.setTextAlign(Paint.Align.CENTER);

        this.timeFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.timeFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE));
        this.timeFont.setTextSize(service.getResources().getDimension(R.dimen.time_font_size));
        this.timeFont.setColor(service.getResources().getColor(R.color.hour_colour));
        this.timeFont.setTextAlign(Paint.Align.CENTER);

        this.dateFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.dateFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE));
        this.dateFont.setTextSize(service.getResources().getDimension(R.dimen.date_font_size));
        this.dateFont.setColor(service.getResources().getColor(R.color.time_colour));
        this.dateFont.setTextAlign(Paint.Align.CENTER);

        this.weekdayFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.weekdayFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE));
        this.weekdayFont.setTextSize(service.getResources().getDimension(R.dimen.date_font_size));
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
        String date = String.format("%02d - %02d", day, month);
        canvas.drawText(date, leftDate, topDate, this.dateFont);

        String weekday = String.format("%S", new SimpleDateFormat("EE").format(calendar.getTime()));
        canvas.drawText(weekday, leftWeekday, topWeekday, this.weekdayFont);
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        SlptPictureView background = new SlptPictureView();
        background.setImagePicture(Util.assetToBytes(service, "background_heart_splt.png"));
        //background.setImagePicture(Util.assetToBytes(service, "background_steps_splt.png"));
        //background.setImagePicture(Util.assetToBytes(service, "background_calories_splt.png"));
        SlptLinearLayout hourLayout = new SlptLinearLayout();
        hourLayout.add(new SlptHourHView());
        hourLayout.add(new SlptHourLView());
        hourLayout.setStringPictureArrayForAll(this.digitalNums);

        SlptLinearLayout minuteLayout = new SlptLinearLayout();
        minuteLayout.add(new SlptMinuteHView());
        minuteLayout.add(new SlptMinuteLView());
        minuteLayout.setStringPictureArrayForAll(this.digitalNums);

        Typeface timeTypeFace = ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.BEBAS_NEUE);

        hourLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.time_font_size),
                -16777216,
                timeTypeFace);
        minuteLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.time_font_size),
                -65536,
                timeTypeFace);
        hourLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.time_hour_left_slpt),
                (int) service.getResources().getDimension(R.dimen.time_hour_top_slpt));
        hourLayout.alignX = 2;
        hourLayout.alignY = 0;
        hourLayout.setRect(320,160);
        minuteLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.time_minute_left_slpt),
                (int) service.getResources().getDimension(R.dimen.time_minute_top_slpt));
        minuteLayout.alignX=2;
        minuteLayout.alignY=0;
        minuteLayout.setRect(320,160);

        SlptPictureView dash = new SlptPictureView();
        dash.setStringPicture(" -");

        SlptLinearLayout dayLayout = new SlptLinearLayout();
        dayLayout.add(new SlptDayHView());
        dayLayout.add(new SlptDayLView());
        dayLayout.add(dash);

        SlptLinearLayout monthLayout = new SlptLinearLayout();
        monthLayout.add(new SlptMonthHView());
        monthLayout.add(new SlptMonthLView());



        for (SlptLayout component : Arrays.asList(monthLayout, dayLayout)) {
            component.setTextAttrForAll(
                    service.getResources().getDimension(R.dimen.date_font_size),
                    -16777216,
                    timeTypeFace
            );
        }

        dayLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.date_left_slpt),
                (int) service.getResources().getDimension(R.dimen.date_top_slpt));
        monthLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.month_left_slpt),
                (int) service.getResources().getDimension(R.dimen.month_top_slpt));


        SlptLinearLayout WeekdayLayout = new SlptLinearLayout();
        WeekdayLayout.add(new SlptWeekView());
        WeekdayLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.week_left_slpt),
                (int) service.getResources().getDimension(R.dimen.week_top_slpt));
        WeekdayLayout.setTextAttrForAll(service.getResources().getDimension(R.dimen.date_font_size),
                -16777216,
                timeTypeFace
        );
        WeekdayLayout.alignX = (byte) 2;
        WeekdayLayout.alignY = (byte) 0;
        WeekdayLayout.setRect(50, 40);
        return Arrays.asList(background, hourLayout, minuteLayout, dayLayout, monthLayout, WeekdayLayout);
    }
}
