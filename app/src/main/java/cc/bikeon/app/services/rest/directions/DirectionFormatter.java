package cc.bikeon.app.services.rest.directions;

import android.location.Location;

/**
 * Created by cristianoliveira on 31/05/15.
 */
public class DirectionFormatter {

    public static String fromLocation(Location location)
    {
        StringBuilder locationString = new StringBuilder();
        locationString.append(location.getLatitude());
        locationString.append(",");
        locationString.append(location.getLongitude());

        return locationString.toString();
    }
}
