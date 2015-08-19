package cc.bikeon.app.domain.directions;

import com.google.gson.annotations.SerializedName;

/**
 * Represent a straight route between two points
 * Created by cristianoliveira on 19/08/15.
 */
public class Step {

    @SerializedName("start_location")
    Coordinate startPoint;

    @SerializedName("end_location")
    Coordinate endPoint;

    public Coordinate getStartPoint() {
        return startPoint;
    }

    public Coordinate getEndPoint() {
        return endPoint;
    }
}
