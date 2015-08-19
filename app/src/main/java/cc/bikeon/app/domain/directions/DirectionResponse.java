package cc.bikeon.app.domain.directions;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cc.bikeon.app.domain.Coordinate;
import cc.bikeon.app.services.rest.RestResponse;

/**
 * Created by cristianoliveira on 01/06/15.
 */
public class DirectionResponse implements RestResponse<Coordinate> {

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
