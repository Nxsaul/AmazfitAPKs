package es.LBA97.PSourceInverted.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.sport.SlptPowerNumView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.LBA97.PSourceInverted.R;
import es.LBA97.PSourceInverted.data.Battery;
import es.LBA97.PSourceInverted.data.DataType;
import es.LBA97.PSourceInverted.resource.ResourceManager;

/**
 * Battery widget
 */

public class BatteryWidget extends AbstractWidget {
    private Battery batteryData;
    private TextPaint batteryFont;
    private Drawable batteryIcon0;
    private Drawable batteryIcon10;
    private Drawable batteryIcon20;
    private Drawable batteryIcon30;
    private Drawable batteryIcon40;
    private Drawable batteryIcon50;
    private Drawable batteryIcon60;
    private Drawable batteryIcon70;
    private Drawable batteryIcon80;
    private Drawable batteryIcon90;
    private Drawable batteryIcon100;
    private float leftBattery;
    private float topBattery;
    private String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private String sBattery;
    private int BatteryNum;

    public void init(Service service) {
        this.leftBattery = service.getResources().getDimension(R.dimen.battery_icon_left);
        this.topBattery = service.getResources().getDimension(R.dimen.battery_icon_top);
        this.batteryIcon0 = service.getResources().getDrawable(R.drawable.battery0, null);
        this.batteryIcon10 = service.getResources().getDrawable(R.drawable.battery10, null);
        this.batteryIcon20 = service.getResources().getDrawable(R.drawable.battery20, null);
        this.batteryIcon30 = service.getResources().getDrawable(R.drawable.battery30, null);
        this.batteryIcon40 = service.getResources().getDrawable(R.drawable.battery40, null);
        this.batteryIcon50 = service.getResources().getDrawable(R.drawable.battery50, null);
        this.batteryIcon60 = service.getResources().getDrawable(R.drawable.battery60, null);
        this.batteryIcon70 = service.getResources().getDrawable(R.drawable.battery70, null);
        this.batteryIcon80 = service.getResources().getDrawable(R.drawable.battery80, null);
        this.batteryIcon90 = service.getResources().getDrawable(R.drawable.battery90, null);
        this.batteryIcon100 = service.getResources().getDrawable(R.drawable.battery100, null);
        this.batteryIcon0.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon10.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon20.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon30.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon40.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon50.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon60.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon70.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon80.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon90.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryIcon100.setBounds((int) this.leftBattery, (int) this.topBattery, ((int) this.leftBattery) + 26, ((int) this.topBattery) + 14);
        this.batteryFont = new TextPaint(1);
        this.batteryFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE));
        this.batteryFont.setTextSize(service.getResources().getDimension(R.dimen.date_font_size));
        this.batteryFont.setColor(service.getResources().getColor(R.color.hour_colour));
        this.batteryFont.setTextAlign(Paint.Align.CENTER);
    }

    public void onDataUpdate(DataType type, Object value) {
        this.batteryData = (Battery) value;
    }

    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.BATTERY);
    }

    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        if (this.batteryData != null) {
            this.sBattery = String.format("%02d", new Object[]{Integer.valueOf((this.batteryData.getLevel() * 100) / this.batteryData.getScale())});
            if (sBattery.equals("100")) {
                this.batteryIcon100.draw(canvas);
            } else {
                if (sBattery.substring(0, 1).equals("0")) {
                    this.batteryIcon0.draw(canvas);
                }
                if (sBattery.substring(0, 1).equals("1")) {
                    this.batteryIcon10.draw(canvas);
                }
                if (sBattery.substring(0, 1).equals("2")) {
                    this.batteryIcon20.draw(canvas);
                }
                if (sBattery.substring(0, 1).equals("3")) {
                    this.batteryIcon30.draw(canvas);
                }
                if (sBattery.substring(0, 1).equals("4")) {
                    this.batteryIcon40.draw(canvas);
                }
                if (sBattery.substring(0, 1).equals("5")) {
                    this.batteryIcon50.draw(canvas);
                }
                if (sBattery.substring(0, 1).equals("6")) {
                    this.batteryIcon60.draw(canvas);
                }
                if (sBattery.substring(0, 1).equals("7")) {
                    this.batteryIcon70.draw(canvas);
                }
                if (sBattery.substring(0, 1).equals("8")) {
                    this.batteryIcon80.draw(canvas);
                }
                if (sBattery.substring(0, 1).equals("9")) {
                    this.batteryIcon90.draw(canvas);
                }
            }
        }
        this.BatteryNum = Integer.parseInt(this.sBattery);
    }

    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        byte[] SlptBlank = Util.assetToBytes(service.getApplicationContext(), "blank.png");
        byte[] SlptZero = Util.assetToBytes(service.getApplicationContext(), "zero.png");
        byte[] SlptBatteryIcon100 = Util.assetToBytes(service.getApplicationContext(), "battery100.png");
        byte[] SlptBatteryIcon90 = Util.assetToBytes(service.getApplicationContext(), "battery90.png");
        byte[] SlptBatteryIcon80 = Util.assetToBytes(service.getApplicationContext(), "battery80.png");
        byte[] SlptBatteryIcon70 = Util.assetToBytes(service.getApplicationContext(), "battery70.png");
        byte[] SlptBatteryIcon60 = Util.assetToBytes(service.getApplicationContext(), "battery60.png");
        byte[] SlptBatteryIcon50 = Util.assetToBytes(service.getApplicationContext(), "battery50.png");
        byte[] SlptBatteryIcon40 = Util.assetToBytes(service.getApplicationContext(), "battery40.png");
        byte[] SlptBatteryIcon30 = Util.assetToBytes(service.getApplicationContext(), "battery30.png");
        byte[] SlptBatteryIcon20 = Util.assetToBytes(service.getApplicationContext(), "battery20.png");
        byte[] SlptBatteryIcon10 = Util.assetToBytes(service.getApplicationContext(), "battery10.png");
        byte[] SlptBatteryIcon0 = Util.assetToBytes(service.getApplicationContext(), "battery0.png");
        byte[][] SlptBatteryArray = new byte[][]{SlptBatteryIcon0, SlptBatteryIcon10, SlptBatteryIcon20, SlptBatteryIcon30, SlptBatteryIcon40, SlptBatteryIcon50, SlptBatteryIcon60, SlptBatteryIcon70, SlptBatteryIcon80, SlptBatteryIcon90};
        byte[][] SlptBatteryArray100 = new byte[][]{SlptZero, SlptBatteryIcon100, SlptBlank, SlptBlank, SlptBlank, SlptBlank, SlptBlank, SlptBlank, SlptBlank, SlptBlank};

        SlptLinearLayout BatteryIcon10 = new SlptLinearLayout();
        BatteryIcon10.add(new SlptPowerNumView());
        BatteryIcon10.setImagePictureArrayForAll(SlptBatteryArray);
        BatteryIcon10.setStart(-17, 17);

        SlptLinearLayout BatteryIcon100 = new SlptLinearLayout();
        BatteryIcon100.add(new SlptPowerNumView());
        BatteryIcon100.setImagePictureArrayForAll(SlptBatteryArray100);
        BatteryIcon100.setStart(-17, 17);
        BatteryIcon100.alignX = (byte) 1;
        BatteryIcon100.alignY = (byte) 0;
        BatteryIcon100.setRect(960, 320);

        return Arrays.asList(new SlptViewComponent[]{BatteryIcon100, BatteryIcon10});
    }
}
