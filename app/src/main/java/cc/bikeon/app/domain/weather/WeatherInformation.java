package cc.bikeon.app.domain.weather;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by cristianoliveira on 12/05/15.
 */
public class WeatherInformation {
    @SerializedName("coord")
    private JsonObject coord;

    @SerializedName("sys")
    private JsonObject sys;

    @SerializedName("weather")
    private ArrayList<Weather> weather;

    @SerializedName("main")
    private Temperature temperature;

    @SerializedName("wind")
    private JsonObject wind;

    @SerializedName("name")
    private String strCityName;

    public JsonObject getCoord()
    {
        return coord;
    }

    public JsonObject getSys()
    {
        return sys;
    }

    public ArrayList<Weather> getWeather()
    {
        return weather;
    }

    public Temperature getTemperature()
    {
        return temperature;
    }

    public JsonObject getWind()
    {
        return wind;
    }

    public String getStrCityName()
    {
        return strCityName;
    }
}
