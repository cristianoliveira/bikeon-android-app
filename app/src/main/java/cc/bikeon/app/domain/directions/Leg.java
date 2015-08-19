package cc.bikeon.app.domain.directions;

import com.google.gson.annotations.SerializedName;

/**
 * Represent a Leg for Route. Each route has one or more legs
 * Each leg has steps that represent squares/avenues/streets
 * Created by cristianoliveira on 19/08/15.
 */
public class Leg {
    @SerializedName("steps")
    Step[] steps;

    public Step[] getSteps() {
        return steps;
    }
}
