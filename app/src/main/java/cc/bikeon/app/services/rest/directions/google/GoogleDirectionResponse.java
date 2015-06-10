package cc.bikeon.app.services.rest.directions.google;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import cc.bikeon.app.services.rest.directions.DirectionParser;
import cc.bikeon.app.services.rest.directions.GeocodeResponse;

/**
 * Created by cristianoliveira on 06/06/15.
 */
public class GoogleDirectionResponse implements GeocodeResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("routes")
    private List<JsonObject> routes;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public List<LatLng> getData() {

        JsonArray legs = routes.get(0).getAsJsonArray("legs");

        List<LatLng> directions = null;

        if (legs.size() > 0) {

            directions = new ArrayList<LatLng>();

            for (int i = 0; i < legs.size(); i++) {
                JsonObject leg = (JsonObject)legs.get(i);

                JsonArray steps = leg.getAsJsonArray("steps");

                for (int j = 0; j < steps.size(); j++) {
                    JsonObject step = (JsonObject) steps.get(i);

                    JsonObject startPoint = step.getAsJsonObject("start_location");

                    LatLng start = DirectionParser.parseToLatLng(startPoint);
                    directions.add(start);

                    JsonObject endPoint = step.getAsJsonObject("end_location");

                    LatLng end = DirectionParser.parseToLatLng(endPoint);
                    directions.add(end);

                }
            }
        }

        return directions;
    }
}
