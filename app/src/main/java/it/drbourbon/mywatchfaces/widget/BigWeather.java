package it.drbourbon.mywatchfaces.widget;

import android.app.Service;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.Log;

import com.huami.watch.watchface.widget.slpt.SlptWeatherWidget;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

import java.util.Collections;
import java.util.List;

import es.malvarez.mywatchfaces.data.DataType;
import es.malvarez.mywatchfaces.data.Steps;
import es.malvarez.mywatchfaces.resource.ResourceManager;
import es.malvarez.mywatchfaces.widget.AbstractWidget;

import com.ravenliquid.watchfaces.R;

/**
 * Created by fabio on 23/05/17.
 */

// SlptWeatherWidget
public class BigWeather extends AbstractWidget {
    private WeatherData weather;
    private TextPaint weatherFont;
    private String weathers[];

    /*
    private final String[] weatherIconNames = new String[]{
            "wf_weather_sunny.png",
            "wf_weather_cloudy.png",
            "wf_weather_overcast.png",
            "wf_weather_fog.png",
            "wf_weather_smog.png",
            "wf_weather_shower.png",
            "wf_weather_thunder_shower.png",
            "wf_weather_light_rain.png",
            "wf_weather_moderate_rain.png",
            "wf_weather_heavy_rain.png",
            "wf_weather_rainstorm.png", // 10
            "wf_weather_torrential_rain.png",
            "wf_weather_sleet.png",
            "wf_weather_freezing_rain.png",
            "wf_weather_hail.png",
            "wf_weather_light_snow.png",
            "wf_weather_moderate_snow.png",
            "wf_weather_heavy_snow.png",
            "wf_weather_snowstorm.png",
            "wf_weather_dust.png",
            "wf_weather_blowing_sand.png", // 20
            "wf_weather_sand_storm.png",
            "wf_weather_unknown.png" // 22
    };
    */

    @Override
    public void init(Service service) {
        this.weatherFont = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        this.weatherFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), ResourceManager.Font.OPEN24));
        this.weatherFont.setTextSize(service.getResources().getDimension(R.dimen.weather_font_size));
        this.weatherFont.setColor(service.getResources().getColor(R.color.threelines_time_color));
        this.weatherFont.setTextAlign(Paint.Align.CENTER);
        this.weathers = service.getResources().getStringArray(R.array.weather);
    }

    @Override
    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.WEATHER);
    }

    @Override
    public void onDataUpdate(DataType type, Object value) {
        this.weather = (WeatherData) value;
        //Log.e("DRB", "Got weather data: " + weather.toString());
    }

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        if(weather!=null && weather.weatherType<22){
            String sWeather = weathers[weather.weatherType];
            canvas.drawText(sWeather, width/2, 62, this.weatherFont);
        }
    }

    @Override
    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        /*
        int x=0,y=0,width=84,height=84,model=0;
        SlptWeatherWidget weather = new SlptWeatherWidget(service.getApplicationContext(), x, y, width, height, model);
        */
        return null;
    }
}
