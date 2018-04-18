package es.LBA97.timetravelinverted.data;

public class WeatherData {
    public String tempFlag;
    public String tempString;
    public int weatherType;

    public WeatherData(String tempFlag, String tempString, int weatherType) {
        this.tempFlag = tempFlag;
        this.tempString = tempString;
        this.weatherType = weatherType;
    }

    public String toString() {
        return String.format("weather info [tempFlag:%s, tempString:%s, weatherType:%d", new Object[]{this.tempFlag, this.tempString, Integer.valueOf(this.weatherType)});
    }
}
