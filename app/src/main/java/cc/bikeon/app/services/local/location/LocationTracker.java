package cc.bikeon.app.services.local.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristianoliveira on 25/05/15.
 */
public class LocationTracker {

    private LocationManager locationManager;

    public LocationTracker(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public boolean isGpsLocationServiceEnabled()
    {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public boolean isNetworkLocationServiceEnabled()
    {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public boolean isLocationServiceEnabled()
    {
        return isGpsLocationServiceEnabled() || isNetworkLocationServiceEnabled();
    }

    public boolean startListener(LocationListener locationLinstener){

        boolean isGpsEnabled = isLocationServiceEnabled();

        if(isGpsLocationServiceEnabled())
        {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER
                    , LocationConstants.TIME_SECONDS_REFRESH
                    , LocationConstants.MIN_DISTANCE_REFRESH
                    , locationLinstener);

            Location lastLocation =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            locationLinstener.onLocationChanged(lastLocation);

            return true;

        }else if(isLocationServiceEnabled()){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER
                    , LocationConstants.TIME_SECONDS_REFRESH
                    , LocationConstants.MIN_DISTANCE_REFRESH
                    , locationLinstener);

            Location lastLocation =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            locationLinstener.onLocationChanged(lastLocation);

            return true;
        }

        return false;
    }

    public Location getLastKnowLocation()
    {
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location == null)
        {
            location =  locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        return location;
    }
}
