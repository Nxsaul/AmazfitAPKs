package it.drbourbon.mywatchfaces.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import com.huami.watch.watchface.util.Util;
import com.huami.watch.watchface.widget.slpt.SlptWeatherWidget;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import es.malvarez.mywatchfaces.data.DataType;
import es.malvarez.mywatchfaces.resource.ResourceManager;
import es.malvarez.mywatchfaces.widget.AbstractWidget;

import com.ravenliquid.watchfaces.R;

/**
 * Created by fabio on 23/05/17.
 */

// SlptWeatherWidget
public class BigImageWeather extends AbstractWidget {
    private WeatherData weather;
    private float verticalOffset;

    public BigImageWeather(float verticalOffset) {
        this.verticalOffset = verticalOffset;
    }

    private Drawable[] weatherIcons;
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
    private final int[] weatherIconIds = new int[]{
            R.drawable.weather_sunny,
            R.drawable.weather_cloudy,
            R.drawable.weather_overcast,
            R.drawable.weather_fog,
            R.drawable.weather_smog,
            R.drawable.weather_shower,
            R.drawable.weather_thunder_shower,
            R.drawable.weather_light_rain,
            R.drawable.weather_rain,
            R.drawable.weather_rain,
            R.drawable.weather_rain,
            R.drawable.weather_rain,
            R.drawable.weather_sleet,
            R.drawable.weather_sleet,
            R.drawable.weather_hail,
            R.drawable.weather_light_snow,
            R.drawable.weather_snow,
            R.drawable.weather_snow,
            R.drawable.weather_snow,
            R.drawable.weather_dust,
            R.drawable.weather_dust,
            R.drawable.weather_dust,
    };

    @Override
    public void init(Service service) {
        List<Drawable> dd = new ArrayList<>(weatherIconIds.length);
        for (int resId : weatherIconIds){
            dd.add(service.getDrawable(resId));
        }
        weatherIcons = dd.toArray(new Drawable[0]);
    }

    @Override
    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.WEATHER);
    }

    @Override
    public void onDataUpdate(DataType type, Object value) {
        this.weather = (WeatherData) value;
    }

    @Override
    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        if(weather!=null && weather.weatherType<22){
            //int idx = ThreadLocalRandom.current().nextInt(0,weatherIcons.length);
            int idx = weather.weatherType;
            Drawable weatherIcon = weatherIcons[idx];

            float y = verticalOffset; //160 + 16;//160 - 120;
            float x = 160 - 175/2;
            canvas.drawBitmap(Util.drawableToBitmap(weatherIcon), x, y, null);
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
