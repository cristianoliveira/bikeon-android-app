package cc.bikeon.app.internal.extractors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collection;
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
public class DirectionsExtractor implements Extractor<List<Coordinate>, Collection<Route>> {

    /**
     * Extract Coordination Points from a Collection of Routes
     * @param Collection<Routes> of roiutes
     * @return list of coordinates or empty if has no routes.
     */
    public List<Coordinate> extract(Collection<Route> routes) {
        List<Coordinate> directions = new ArrayList<Coordinate>();

        if (!routes.isEmpty()) {
            Route route = (Route) routes.iterator().next();
            Leg[] legs = route.getLegs();

            if (legs.length > 0) {

                for (int i = 0; i < legs.length; i++) {
                    Leg leg = legs[i];

                    Step[] steps = leg.getSteps();

                    for (int j = 0; j < steps.length; j++) {
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
