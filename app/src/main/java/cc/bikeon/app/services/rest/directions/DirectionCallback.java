package cc.bikeon.app.services.rest.directions;

import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;

/**
 * Callback for {@ling DirectionRequester.class} requests
 *
 * Created by cristianoliveira on 01/07/15.
 */
public interface DirectionCallback {
    void onSuccess(List<Coordinate> directions);
    void onFailure(String error);
}
