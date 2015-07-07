package cc.bikeon.app.services.rest.directions.google;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import cc.bikeon.app.domain.Coordinate;
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
    public List<Coordinate> getData() {
        Gson parser = new Gson();

        JsonArray legs = routes.get(0).getAsJsonArray("legs");

        List<Coordinate> directions = null;

        if (legs.size() > 0) {

            directions = new ArrayList<Coordinate>();

            for (int i = 0; i < legs.size(); i++) {
                JsonObject leg = (JsonObject)legs.get(i);

                JsonArray steps = leg.getAsJsonArray("steps");

                for (int j = 0; j < steps.size(); j++) {
                    JsonObject step = (JsonObject) steps.get(i);

                    JsonObject startPoint = step.getAsJsonObject("start_location");
                    directions.add(parser.fromJson(startPoint, Coordinate.class));

                    JsonObject endPoint = step.getAsJsonObject("end_location");
                    directions.add(parser.fromJson(endPoint, Coordinate.class));

                }
            }
        }

        return directions;
    }
}
