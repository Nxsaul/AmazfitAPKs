package es.LBA97.PSourceInverted.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.sport.SlptPowerNumView;
import com.ingenic.iwds.slpt.view.sport.SlptTodaySportDistanceFView;
import com.ingenic.iwds.slpt.view.sport.SlptTodaySportDistanceLView;
import com.ingenic.iwds.slpt.view.sport.SlptTodayStepNumView;
import com.ingenic.iwds.slpt.view.sport.SlptTotalDistanceFView;
import com.ingenic.iwds.slpt.view.sport.SlptTotalDistanceLView;

import java.util.Arrays;
import java.util.List;

import es.LBA97.PSourceInverted.R;
import es.LBA97.PSourceInverted.data.Battery;
import es.LBA97.PSourceInverted.data.DataType;
import es.LBA97.PSourceInverted.data.Steps;
import es.LBA97.PSourceInverted.data.TodayDistance;
import es.LBA97.PSourceInverted.data.TotalDistance;
import es.LBA97.PSourceInverted.resource.ResourceManager;

public class CirclesWidget extends AbstractWidget {

    private final static int MARGIN = 1;

    private Paint ring;
    private Paint circle;
    private Paint textPaint;
    private Paint roadPaint;
    private int backgroundColour;
    private int batteryColour;
    private int stepsColour;
    private int sportColour;
    private int thickness;

    private Battery batteryData;
    private Float batterySweepAngle = null;
 //   private Drawable batteryIcon;

    private Steps stepsData;
    private Float stepsSweepAngle = null;
 //   private Drawable stepsIcon;

    private TodayDistance sportData;
    private Float sportSweepAngle = null;
   // private Drawable sportIcon;

    private TotalDistance roadData;

    private final float startAngleBattery = 30;
    private final float arcSizeBattery = 360 - startAngleBattery - startAngleBattery;

    private final float startAngleSteps = startAngleBattery + 3;
    private final float arcSizeSteps = 360 - startAngleSteps - startAngleSteps;

    private final float startAngleSport = startAngleSteps + 3;
    private final float arcSizeSport = 360 - startAngleSport - startAngleSport;

    private float batteryTextLeft;
    private float batteryTextTop;
    private float stepsTextLeft;
    private float stepsTextTop;
    private float sportTextLeft;
    private float sportTextTop;
    private float roadTextLeft;
    private float roadTextTop;


    @Override
    public void init(Service service) {
        this.thickness = (int) service.getResources().getDimension(R.dimen.circles_thickness);

        this.backgroundColour = service.getResources().getColor(R.color.circles_background);

        this.batteryColour = service.getResources().getColor(R.color.circles_battery_colour);
        //  this.batteryIcon = service.getResources().getDrawable(R.drawable.battery, null);
        // this.setDrawableBounds(this.batteryIcon, service.getResources().getDimension(R.dimen.battery_icon_left), service.getResources().getDimension(R.dimen.battery_icon_top));

        this.stepsColour = service.getResources().getColor(R.color.circles_steps_colour);
        //   this.stepsIcon = service.getResources().getDrawable(R.drawable.steps, null);
        //this.setDrawableBounds(this.stepsIcon, service.getResources().getDimension(R.dimen.steps_icon_left), service.getResources().getDimension(R.dimen.steps_icon_top));

        this.sportColour = service.getResources().getColor(R.color.circles_sport_colour);
        //  this.sportIcon = service.getResources().getDrawable(R.drawable.sport, null);
        // this.setDrawableBounds(this.sportIcon, service.getResources().getDimension(R.dimen.sport_icon_left), service.getResources().getDimension(R.dimen.sport_icon_top));

        this.textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.textPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE));
        this.textPaint.setTextSize(service.getResources().getDimension(R.dimen.circles_font_size));
        this.textPaint.setColor(service.getResources().getColor(R.color.time_colour));
        this.textPaint.setTextAlign(Paint.Align.CENTER);

        this.roadPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.roadPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE));
        this.roadPaint.setTextSize(service.getResources().getDimension(R.dimen.circles_font_size));
        this.roadPaint.setColor(service.getResources().getColor(R.color.time_colour));
        this.roadPaint.setTextAlign(Paint.Align.RIGHT);

        this.ring = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.ring.setStrokeCap(Paint.Cap.ROUND);
        this.ring.setStyle(Paint.Style.STROKE);
        this.ring.setStrokeWidth(this.thickness);

        this.circle = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.circle.setColor(Color.BLACK);
        this.circle.setStrokeWidth(1f);
        this.circle.setStyle(Paint.Style.STROKE);

        this.batteryTextLeft = service.getResources().getDimension(R.dimen.battery_text_left);
        this.batteryTextTop = service.getResources().getDimension(R.dimen.battery_text_top);
        this.stepsTextLeft = service.getResources().getDimension(R.dimen.steps_text_left);
        this.stepsTextTop = service.getResources().getDimension(R.dimen.steps_text_top);
        this.sportTextLeft = service.getResources().getDimension(R.dimen.sport_text_left);
        this.sportTextTop = service.getResources().getDimension(R.dimen.sport_text_top);
        this.roadTextLeft = service.getResources().getDimension(R.dimen.road_text_left);
        this.roadTextTop = service.getResources().getDimension(R.dimen.road_text_top);
    }

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        int count = canvas.save();

        int radius = Math.round(Math.min(width / 2, height / 2)) - this.thickness;

        RectF oval = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        // rotate from 0 to 270 degrees
        canvas.rotate(90, centerX, centerY);

        canvas.restoreToCount(count);

        // this.batteryIcon.draw(canvas);
        //this.stepsIcon.draw(canvas);
        // this.sportIcon.draw(canvas);

        if (this.batteryData != null) {
            String text = String.format("%02d%%", this.batteryData.getLevel() * 100 / this.batteryData.getScale());
            canvas.drawText(text, batteryTextLeft, batteryTextTop, textPaint);
        }

        if (this.stepsData != null) {
            String text = String.format("%s", this.stepsData.getSteps());
            canvas.drawText(text, stepsTextLeft, stepsTextTop, textPaint);
        }

        if (this.sportData != null) {
            String text = String.format("%.2f", this.sportData.getDistance());
            canvas.drawText(text, sportTextLeft, sportTextTop, textPaint);
        }

        if (this.roadData != null) {
            String text = String.format("%.2f km", this.roadData.getDistance());
            canvas.drawText(text, roadTextLeft, roadTextTop, roadPaint);
        }else{
            String text = String.format("0.00 km");
            canvas.drawText(text, roadTextLeft, roadTextTop, roadPaint);
        }
    }

    @Override
    public void onDataUpdate(DataType type, Object value) {
        switch (type) {
            case STEPS:
                onSteps((Steps) value);
                break;
            case BATTERY:
                onBatteryData((Battery) value);
                break;
            case DISTANCE:
                onDistanceData((TodayDistance) value);
                break;
            case TOTAL_DISTANCE:
                onTotaldistanceData((TotalDistance) value);
                break;
        }
    }

    @Override
    public List<DataType> getDataTypes() {
        return Arrays.asList(DataType.BATTERY, DataType.STEPS, DataType.DISTANCE, DataType.TOTAL_DISTANCE);
    }


    private void onSteps(Steps steps) {
        this.stepsData = steps;
        if (stepsData == null || stepsData.getTarget() == 0) {
            this.stepsSweepAngle = 0f;
        } else {
            this.stepsSweepAngle = Math.min(arcSizeSteps, arcSizeSteps * (stepsData.getSteps() / (float) stepsData.getTarget()));
        }
    }

    private void onBatteryData(Battery battery) {
        this.batteryData = battery;
        if (batteryData == null) {
            this.batterySweepAngle = 0f;
        } else {
            float scale = batteryData.getLevel() / (float) batteryData.getScale();
            this.batterySweepAngle = Math.min(arcSizeBattery, arcSizeBattery * scale);
        }
    }

    private void onDistanceData(TodayDistance distance) {
        this.sportData = distance;
        if (sportData == null || sportData.getDistance() <= 0) {
            this.sportSweepAngle = 0f;
        } else {
            double scale = sportData.getDistance() / 3.0d;
            this.sportSweepAngle = (float) Math.min(arcSizeSport, arcSizeSport * scale);
        }
    }

    private void onTotaldistanceData (TotalDistance distance){
        this.roadData = distance;
    }


    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        Typeface timeTypeFace = ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE);

        SlptPictureView point = new SlptPictureView();
        point.setStringPicture(".");

        SlptLinearLayout power = new SlptLinearLayout();
        SlptPictureView percentage = new SlptPictureView();
        percentage.setStringPicture("%");
        power.alignX = 2;
        power.alignY = 2;
        power.add(new SlptPowerNumView());
        power.add(percentage);
        power.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.circles_font_size_slpt),
                -16777216,
                timeTypeFace
        );
        power.setStart(
                (int) service.getResources().getDimension(R.dimen.battery_text_left_slpt),
                (int) service.getResources().getDimension(R.dimen.battery_text_top_slpt));
        power.alignX= 2;
        power.alignY= 0;
        power.setRect(50, 30);

        SlptLinearLayout steps = new SlptLinearLayout();
        steps.alignX = (byte)1;
        steps.alignY = (byte)2;
       // steps.setRect(80,80);
        steps.add(new SlptTodayStepNumView());
        steps.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.circles_font_size_slpt),
                -16777216,
                timeTypeFace
        );

        steps.setStart(
                (int) service.getResources().getDimension(R.dimen.steps_text_left_slpt),
                (int) service.getResources().getDimension(R.dimen.steps_text_top_slpt));
        steps.alignX= (byte) 2;
        steps.alignY= (byte) 2;
        steps.setRect(64,22);

        SlptLinearLayout sport = new SlptLinearLayout();
        sport.add(new SlptTodaySportDistanceFView());
        sport.add(point);
        sport.add(new SlptTodaySportDistanceLView());
        sport.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.circles_font_size_slpt),
                -16777216,
                timeTypeFace
        );
        sport.setStart(
                (int) service.getResources().getDimension(R.dimen.sport_text_left_slpt),
                (int) service.getResources().getDimension(R.dimen.sport_text_top_slpt));


        SlptLinearLayout road = new SlptLinearLayout();
        SlptPictureView kilometer = new SlptPictureView();
        kilometer.setStringPicture(" km");
        road.add(new SlptTotalDistanceFView());
        road.add(point);
        road.add(new SlptTotalDistanceLView());
        road.add(kilometer);
        road.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.circles_font_size_slpt),-16777216,timeTypeFace
        );
        road.setStart(
                (int) service.getResources().getDimension(R.dimen.road_text_left_slpt),
                (int) service.getResources().getDimension(R.dimen.road_text_top_slpt)
        );
        road.alignX = 1;
        road.alignY = 0;
        road.setRect(100, 30);

        return Arrays.asList(new SlptViewComponent[]{steps, sport, road, power});
    }
}