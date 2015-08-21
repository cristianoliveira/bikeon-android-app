package cc.bikeon.app.internal.extractors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;
import cc.bikeon.app.domain.directions.GoogleDirection;
import cc.bikeon.app.domain.directions.Leg;
import cc.bikeon.app.domain.directions.Route;
import cc.bikeon.app.domain.directions.Step;

/**
 * Responsible for extract formatted data from a Direction
 * Created by cristianoliveira on 19/08/15.
 */
public class DirectionsExtractor {

    private final int FIRST = 0;

    /**
     * Extrac steps from a GoogleDirection Structure
     * @param direction
     * @return list of coordinates
     */
    public List<Coordinate> extractSteps(GoogleDirection direction) {
        List<Coordinate> directions = new ArrayList<Coordinate>();

        Route[] routes = direction.getRoutes();

        if (routes.length > 0) {
            Leg[] legs = routes[FIRST].getLegs();

            if (legs.length > 0) {

                for (int i = FIRST; i < legs.length; i++) {
                    Leg leg = legs[i];

                    Step[] steps = leg.getSteps();

                    for (int j = FIRST; j < steps.length; j++) {
                        Step step = steps[j];

                        directions.add(step.getStartPoint());
                        directions.add(step.getEndPoint());

                    }
                }
            }
        }
        return directions;

    }
}
