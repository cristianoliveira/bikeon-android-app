package cc.bikeon.app.services.rest.directions;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cc.bikeon.app.domain.Coordinate;

/**
 * Created by cristianoliveira on 01/06/15.
 */
public class DirectionResponse implements GeocodeResponse {

    private String status;

    @SerializedName("routes")
    private List<JsonObject> routes;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public List<Coordinate> getData() {
        return null;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
