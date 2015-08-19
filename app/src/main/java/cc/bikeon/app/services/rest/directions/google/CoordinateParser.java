package cc.bikeon.app.services.rest.directions.google;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cc.bikeon.app.domain.Coordinate;

/**
 * Responsible to parse a given JsonObject to Coordinate
 * Created by cristianoliveira on 18/08/15.
 */
public class CoordinateParser {

    /**
     * Parse a list of JsonObjects in to a List of Coordinates
     * @param jsonObjects
     * @return List of Coordinates
     */
    public List<Coordinate> parse(List<JsonObject> jsonObjects) {

        List<Coordinate> directions = new ArrayList<Coordinate>();

        if (jsonObjects.size() > 0) {
            Gson parser = new Gson();

            JsonArray legs = jsonObjects.get(0).getAsJsonArray("legs");

            if (legs.size() > 0) {

                for (int i = 0; i < legs.size(); i++) {
                    JsonObject leg = (JsonObject) legs.get(i);

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
        }
        return directions;
    }

}
