package cc.bikeon.app.services.rest.directions;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cristianoliveira on 01/06/15.
 */
public class DirectionResponse {

    private String status;

    @SerializedName("routes")
    private List<JsonElement> routes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<JsonElement> getRoutes() {
        return routes;
    }

    public void setRoutes(List<JsonElement> routes) {
        this.routes = routes;
    }
}
