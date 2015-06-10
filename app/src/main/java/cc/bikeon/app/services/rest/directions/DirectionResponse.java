package cc.bikeon.app.services.rest.directions;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.maps.model.GeocodingResult;

import java.util.List;

import cc.bikeon.app.services.rest.RestResponse;

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
    public List<LatLng> getData() {
        return null;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
