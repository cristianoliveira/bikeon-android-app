package cc.bikeon.app.domain.directions;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represent a Google Direction Entity from Google Direction API
 * Created by cristianoliveira on 06/06/15.
 */
public class GoogleDirection {

    @SerializedName("status")
    private String status;

    @SerializedName("routes")
    private Route[] routes;

    public String getStatus() {
        return status;
    }

    public Route[] getRoutes() {
        return routes;
    }
}
