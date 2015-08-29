package cc.bikeon.app.domain.directions;

import com.google.android.gms.maps.model.Polyline;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Represent a straight route between two points
 *
 * See:
 * https://developers.google.com/maps/documentation/utilities/polylinealgorithm
 *
 * Created by cristianoliveira on 19/08/15.
 */
public class Step {

    @SerializedName("start_location")
    Coordinate startPoint;

    @SerializedName("end_location")
    Coordinate endPoint;

    @SerializedName("polyline")
    JsonObject polyline;

    public Coordinate getStartPoint() {
        return startPoint;
    }

    public Coordinate getEndPoint() {
        return endPoint;
    }

    public String getEncodedPoints() {
        return polyline.get("points").getAsString();
    }
}
