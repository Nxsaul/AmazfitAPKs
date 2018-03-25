package it.drbourbon.mywatchfaces.widget;

/**
 * Created by fabio on 24/05/17.
 */

public class WeatherData {
    public String tempFlag;
    public String tempString;
    public int weatherType;

    public WeatherData(String tempFlag, String tempString, int weatherType) {
        this.tempFlag = tempFlag;
        this.tempString = tempString;
        this.weatherType = weatherType;
    }

    @Override
    public String toString() {
        return String.format("weather info [tempFlag:%s, tempString:%s, weatherType:%d", tempFlag, tempString, weatherType);
    }
}
