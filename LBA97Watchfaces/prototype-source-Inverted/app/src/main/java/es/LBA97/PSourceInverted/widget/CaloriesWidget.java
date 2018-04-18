package es.LBA97.PSourceInverted.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.sport.SlptTodayCaloriesView;

import java.util.Collections;
import java.util.List;

import es.LBA97.PSourceInverted.R;
import es.LBA97.PSourceInverted.data.Calories;
import es.LBA97.PSourceInverted.data.DataType;
import es.LBA97.PSourceInverted.resource.ResourceManager;

/**
 * Widget de Calories
 */

public class CaloriesWidget extends AbstractWidget {
    private TextPaint textPaint;
    private Calories  calories;

    private float textTop;
    private float textLeft;


    @Override
    public void init(Service service) {
        this.textLeft = service.getResources().getDimension(R.dimen.calories_text_left);
        this.textTop = service.getResources().getDimension(R.dimen.calories_text_top);

        this.textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.textPaint.setColor(service.getResources().getColor(R.color.time_colour));
        this.textPaint.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE));
        this.textPaint.setTextSize(service.getResources().getDimension(R.dimen.circles_font_size));
        this.textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.CALORIES);
    }

    @Override
    public void onDataUpdate(DataType type, Object value) {
        this.calories = (Calories) value;
    }

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        String text = String.format("%s kcal", calories.getCalories());
        canvas.drawText(text, textLeft, textTop, textPaint);
    }
    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        SlptLinearLayout caloriesLayout = new SlptLinearLayout();
        SlptPictureView caloriesUnit = new SlptPictureView();
        caloriesUnit.setStringPicture(" kcal");
        caloriesLayout.add(new SlptTodayCaloriesView());
        caloriesLayout.add(caloriesUnit);
        caloriesLayout.setStart(
                (int) service.getResources().getDimension(R.dimen.calories_text_left_slpt),
                (int) service.getResources().getDimension(R.dimen.calories_text_top_slpt)
        );
        Typeface caloriesFont = ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.PROTOTYPE_SOURCE);
        caloriesLayout.setTextAttrForAll(
                service.getResources().getDimension(R.dimen.circles_font_size_slpt),
                -16777216 ,
                caloriesFont
        );
        caloriesLayout.alignX = 2;
        caloriesLayout.alignY = 0;
        caloriesLayout.setRect(80,23);

        return Collections.<SlptViewComponent>singletonList(caloriesLayout);
    }
}
