package es.LBA97.PSourceInverted.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;


import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.sport.SlptTodayFloorNumView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.LBA97.PSourceInverted.R;
import es.LBA97.PSourceInverted.data.DataType;
import es.LBA97.PSourceInverted.data.TodayFloor;
import es.LBA97.PSourceInverted.resource.ResourceManager;



public class FloorWidget extends AbstractWidget {
    private TextPaint textPaint;
    private TodayFloor todayFloor;
    private String text;
    private int textint;

    private float textTop;
    private float textLeft;

    @Override
    public void init(Service service){
        this.textLeft = service.getResources().getDimension(R.dimen.floors_text_left);
        this.textTop = service.getResources().getDimension(R.dimen.floors_text_top);

        this.textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.textPaint.setColor(service.getResources().getColor(R.color.time_colour));
        this.textPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE));
        this.textPaint.setTextSize(service.getResources().getDimension(R.dimen.circles_font_size));
        this.textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.FLOOR);
    }

    @Override
    public void onDataUpdate (DataType type, Object value) {this.todayFloor = (TodayFloor) value;}

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        this.text = String.format("%s", todayFloor.getFloor());
        canvas.drawText(text, textLeft, textTop, textPaint);

    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        byte[] num0 = Util.assetToBytes(service.getApplicationContext(), "num0.png");
        byte[] num1 = Util.assetToBytes(service.getApplicationContext(), "num1.png");
        byte[] num2 = Util.assetToBytes(service.getApplicationContext(), "num2.png");
        byte[] num3 = Util.assetToBytes(service.getApplicationContext(), "num3.png");
        byte[] num4 = Util.assetToBytes(service.getApplicationContext(), "num4.png");
        byte[] num5 = Util.assetToBytes(service.getApplicationContext(), "num5.png");
        byte[] num6 = Util.assetToBytes(service.getApplicationContext(), "num6.png");
        byte[] num7 = Util.assetToBytes(service.getApplicationContext(), "num7.png");
        byte[] num8 = Util.assetToBytes(service.getApplicationContext(), "num8.png");
        byte[] num9 = Util.assetToBytes(service.getApplicationContext(), "num9.png");
        byte[][] SlptNumArray = new byte[][]{num0, num1, num2, num3, num4, num5, num6, num7, num8, num9};
        SlptLinearLayout floors = new SlptLinearLayout();
        SlptTodayFloorNumView floorsnum = new SlptTodayFloorNumView();

        SlptPictureView floorsunit = new SlptPictureView();
        floors.add(new SlptTodayFloorNumView());
        floors.setImagePictureArrayForAll(SlptNumArray);

        floors.setStart(
                (int) service.getResources().getDimension(R.dimen.floors_text_left_slpt),
                (int) service.getResources().getDimension(R.dimen.floors_text_top_slpt));
        floors.alignX = 2;
        floors.alignY=0;
        floors.setRect(28,21);
        return Arrays.asList(new SlptViewComponent[]{floors});
    }
}
