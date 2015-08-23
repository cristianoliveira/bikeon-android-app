package cc.bikeon.app.services.local.location;

import android.content.Context;
import android.location.LocationManager;

/**
 * Responsible for create a LocationTracker
 * Created by cristianoliveira on 22/08/15.
 */
public class LocationTrakerFactory {
    public static LocationTracker create(Context context) {

        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);

        return new LocationTracker(locationManager);
    }
}
