package cc.bikeon.app.domain.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cristianoliveira on 12/05/15.
 */
public class Temperature {

    double temp;

    @SerializedName("temp_min")
    double tempMin;

    @SerializedName("temp_max")
    double tempMax;

    double pressure;

    @SerializedName("sea_level")
    double seaLevel;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}
