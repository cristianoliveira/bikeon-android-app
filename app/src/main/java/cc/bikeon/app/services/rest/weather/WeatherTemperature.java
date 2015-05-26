package cc.bikeon.app.services.rest.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cristianoliveira on 12/05/15.
 */
public class WeatherTemperature {

    double temp;

    @SerializedName("temp_min")
    double tempMin;

    @SerializedName("temp_max")
    double tempMax;

    double pressure;

    @SerializedName("sea_level")
    double seaLevel;

}
