package cc.bikeon.app.domain.directions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represent a Route for GoogleDirection
 * Created by cristianoliveira on 19/08/15.
 */
public class Route {
    @SerializedName("legs")
    private Leg[] legs;

    public Leg[] getLegs() {
        return legs;
    }
}
