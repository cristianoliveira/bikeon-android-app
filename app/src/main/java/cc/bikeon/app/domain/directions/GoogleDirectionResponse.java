package cc.bikeon.app.domain.directions;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cc.bikeon.app.domain.Coordinate;
import cc.bikeon.app.services.rest.RestResponse;
import cc.bikeon.app.services.rest.directions.google.CoordinateParser;

/**
 * Represent a Google Direction Entity from Google Direction API
 * Created by cristianoliveira on 06/06/15.
 */
public class GoogleDirectionResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("routes")
    private List<JsonObject> routes;

    public String getStatus() {
        return status;
    }

    public List<JsonObject> getRoutes() {
        return routes;
    }
}
