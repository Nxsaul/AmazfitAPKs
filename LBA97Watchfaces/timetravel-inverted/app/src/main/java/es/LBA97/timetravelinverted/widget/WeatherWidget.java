package es.LBA97.timetravelinverted.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.provider.Settings.System;
import android.text.TextPaint;
import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import es.LBA97.timetravelinverted.data.DataType;
import es.LBA97.timetravelinverted.data.WeatherData;
import es.LBA97.timetravelinverted.resource.ResourceManager;
import es.LBA97.timetravelinverted.resource.ResourceManager.Font;
import es.LBA97.timetravelinverted.R;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherWidget extends AbstractWidget {
    private String Tflag;
    private Drawable clock_skin_weather;
    private Drawable clock_skin_weather_cloudy;
    private Drawable clock_skin_weather_fog;
    private Drawable clock_skin_weather_overcast;
    private Drawable clock_skin_weather_rain;
    private Drawable clock_skin_weather_rainsnow;
    private Drawable clock_skin_weather_rainstorm;
    private Drawable clock_skin_weather_showers;
    private Drawable clock_skin_weather_snow;
    private Drawable clock_skin_weather_sunny;
    private Drawable clock_skin_weather_t_storm;
    private Drawable clock_skin_weather_unknown;
    private int font_temperature_size = 20;
    private String sWeather = "--";
    private String tempC;
    private TextPaint temperatureFont;
    private WeatherData weather;
    private TextPaint weatherFont;
    private int weatherType;
    private int weather_text_x = 255;
    private int weather_text_x_slpt = 205;
    private int weather_text_y = 135;
    private int weather_text_y_slpt = 85;
    private int weather_x = 255;
    private int weather_x_slpt = 220;
    private int weather_y = 100;
    private int weather_y_slpt = 65;
    private String[] weathers = new String[]{"Sereno", "Poco Nuvoloso", "Nuvoloso", "Nebbia", "Smog", "Pioggia", "Fulmini", "Pioggia Leggera", "Pioggia Moderata", "Pioggia Intensa", "Temporale", "Pioggia Torrenziale", "Nevischio", "Pioggia Ghiacciata", "Grandine", "Neve Leggera", "Neve Moderata", "Neve Intensa", "Tempesta di neve", "Polvere", "Sabbia", "Tempesta di sabbia", "Sconosciuto"};

    public void init(Service service) {
        this.temperatureFont = new TextPaint(1);
        this.temperatureFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.BEBAS_NEUE));
        this.temperatureFont.setTextSize((float) this.font_temperature_size);
        this.temperatureFont.setColor(service.getResources().getColor(R.color.time_colour));
        this.temperatureFont.setTextAlign(Align.CENTER);
        this.clock_skin_weather_sunny = service.getResources().getDrawable(R.drawable.clock_skin_weather_sunny, null);
        this.clock_skin_weather_cloudy = service.getResources().getDrawable(R.drawable.clock_skin_weather_cloudy, null);
        this.clock_skin_weather_overcast = service.getResources().getDrawable(R.drawable.clock_skin_weather_overcast, null);
        this.clock_skin_weather_fog = service.getResources().getDrawable(R.drawable.clock_skin_weather_fog, null);
        this.clock_skin_weather_showers = service.getResources().getDrawable(R.drawable.clock_skin_weather_showers, null);
        this.clock_skin_weather_t_storm = service.getResources().getDrawable(R.drawable.clock_skin_weather_t_storm, null);
        this.clock_skin_weather_rain = service.getResources().getDrawable(R.drawable.clock_skin_weather_rain, null);
        this.clock_skin_weather_rainstorm = service.getResources().getDrawable(R.drawable.clock_skin_weather_rainstorm, null);
        this.clock_skin_weather_rainsnow = service.getResources().getDrawable(R.drawable.clock_skin_weather_rainsnow, null);
        this.clock_skin_weather_snow = service.getResources().getDrawable(R.drawable.clock_skin_weather_snow, null);
        this.clock_skin_weather_unknown = service.getResources().getDrawable(R.drawable.unknown, null);
    }

    public void onDataUpdate(DataType type, Object value) {
        if (value != null) {
            this.weather = (WeatherData) value;
            this.weatherType = this.weather.weatherType;
            this.tempC = String.format("%sºC", new Object[]{this.weather.tempString});
            if (this.weather.tempString.isEmpty()) {
                this.weatherType = 22;
                this.tempC = "n/a";
                return;
            }
            return;
        }
        this.weather = null;
        this.weatherType = 22;
        this.tempC = String.format("--ºC", new Object[0]);
    }

    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.WEATHER);
    }

    public void draw(Canvas canvas, float width, float height, float centerX, float centerY) {
        this.clock_skin_weather = this.clock_skin_weather_sunny;
        if (this.weatherType == 0) {
            this.clock_skin_weather = this.clock_skin_weather_sunny;
        }
        if (this.weatherType == 1) {
            this.clock_skin_weather = this.clock_skin_weather_cloudy;
        }
        if (this.weatherType == 2) {
            this.clock_skin_weather = this.clock_skin_weather_overcast;
        }
        if (this.weatherType == 3 || this.weatherType == 4 || this.weatherType == 19 || this.weatherType == 20 || this.weatherType == 21) {
            this.clock_skin_weather = this.clock_skin_weather_fog;
        }
        if (this.weatherType == 5 || this.weatherType == 11) {
            this.clock_skin_weather = this.clock_skin_weather_showers;
        }
        if (this.weatherType == 6) {
            this.clock_skin_weather = this.clock_skin_weather_t_storm;
        }
        if (this.weatherType == 7 || this.weatherType == 8) {
            this.clock_skin_weather = this.clock_skin_weather_rain;
        }
        if (this.weatherType == 9 || this.weatherType == 10) {
            this.clock_skin_weather = this.clock_skin_weather_rainstorm;
        }
        if (this.weatherType == 12 || this.weatherType == 13 || this.weatherType == 14) {
            this.clock_skin_weather = this.clock_skin_weather_rainsnow;
        }
        if (this.weatherType == 15 || this.weatherType == 16 || this.weatherType == 17 || this.weatherType == 18) {
            this.clock_skin_weather = this.clock_skin_weather_snow;
        }
        if (this.weatherType == 22) {
            this.clock_skin_weather = this.clock_skin_weather_unknown;
        }
        this.clock_skin_weather.setBounds(this.weather_x - 15, this.weather_y - 15, this.weather_x + 15, this.weather_y + 15);
        this.clock_skin_weather.draw(canvas);
        canvas.drawText(this.tempC, (float) this.weather_text_x, (float) this.weather_text_y, this.temperatureFont);
    }

    private WeatherData getWeatherData(Service service) throws JSONException {
        String weatherObject2;
        JSONObject weatherObject;
        String weatherObject22 = System.getString(service.getApplicationContext().getContentResolver(), "WeatherCheckedSummary");
        if (weatherObject22 != null) {
            weatherObject2 = weatherObject22;
        } else {
            weatherObject2 = weatherObject22;
        }
        while (true) {
            try {
                weatherObject = new JSONObject(weatherObject2);
                break;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        String tempUnit = weatherObject.getString("tempUnit");
        String temp = weatherObject.getString("temp");
        int weatherCodeFrom = weatherObject.getInt("weatherCodeFrom");
        if (weatherCodeFrom < 22) {
            return new WeatherData(tempUnit, temp, weatherCodeFrom);
        }
        return new WeatherData(tempUnit, temp, 22);
    }

    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        Typeface timeTypeFace = ResourceManager.getTypeFace(service.getResources(), Font.BEBAS_NEUE);
        SlptLinearLayout weaLayout = new SlptLinearLayout();
        SlptPictureView var27 = new SlptPictureView();
        try {
            this.weather = getWeatherData(service);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        byte[] SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_sunny.png");
        String sWeather = this.weathers[this.weather.weatherType];
        String sTemp = this.tempC;
        if (this.weather.weatherType == 0) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_sunny.png");
        }
        if (this.weather.weatherType == 1) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_cloudy.png");
        }
        if (this.weather.weatherType == 2) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_overcast.png");
        }
        if (this.weather.weatherType == 3 || this.weather.weatherType == 4 || this.weather.weatherType == 19 || this.weather.weatherType == 20) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_fog.png");
        }
        if (this.weather.weatherType == 5 || this.weather.weatherType == 11) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_showers.png");
        }
        if (this.weather.weatherType == 6) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_t_storm.png");
        }
        if (this.weather.weatherType == 7 || this.weather.weatherType == 8) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_rain.png");
        }
        if (this.weather.weatherType == 9 || this.weather.weatherType == 10) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_rainstorm.png");
        }
        if (this.weather.weatherType == 12 || this.weather.weatherType == 13 || this.weather.weatherType == 14) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_rainsnow.png");
        }
        if (this.weather.weatherType == 15 || this.weather.weatherType == 16 || this.weather.weatherType == 17 || this.weather.weatherType == 18) {
            SlptWeatherImage = Util.assetToBytes(service.getApplicationContext(), "clock_skin_weather_snow.png");
        }
        SlptPictureView WeatherImage = new SlptPictureView();
        WeatherImage.setImagePicture(SlptWeatherImage);
        WeatherImage.alignX = (byte) 2;
        WeatherImage.alignY = (byte) 2;
        WeatherImage.setRect(70, 70);
        WeatherImage.setStart(this.weather_x_slpt, this.weather_y_slpt);
        SlptLinearLayout TemperatureLayout = new SlptLinearLayout();
        SlptPictureView Temperature = new SlptPictureView();
        SlptPictureView TemperatureUnit = new SlptPictureView();
        Temperature.setStringPicture(this.weather.tempString);
        TemperatureUnit.setStringPicture("ºC");
        TemperatureLayout.add(Temperature);
        TemperatureLayout.add(TemperatureUnit);
        TemperatureLayout.alignX = (byte) 2;
        TemperatureLayout.alignY = (byte) 2;
        TemperatureLayout.setRect(100, 80);
        TemperatureLayout.setStart(this.weather_text_x_slpt, this.weather_text_y_slpt);
        TemperatureLayout.setTextAttrForAll((float) this.font_temperature_size, -16777216 , timeTypeFace);
        return Arrays.asList(new SlptViewComponent[]{WeatherImage, TemperatureLayout});
    }
}
