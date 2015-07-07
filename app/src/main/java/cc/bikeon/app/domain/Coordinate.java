package cc.bikeon.app.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cristianoliveira on 26/06/15.
 */
public class Coordinate {

    @SerializedName("lat")
    private double latitude;

    @SerializedName("lng")
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
