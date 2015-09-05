package cc.bikeon.app.services.rest.directions;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionRequester;

/**
 * Responsible for create the Direction Requester.
 * Created by cristianoliveira on 28/08/15.
 */
public class DirectionRequesterFactory {

    public static DirectionRequester create() {
        return new GoogleDirectionRequester(
                BikeOnApplication.getStringResource(R.string.google_maps_key)
        );
    }
}
