package cc.bikeon.app.presenter;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import cc.bikeon.app.R;
import cc.bikeon.app.internal.formatters.DirectionFormatter;
import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.views.LocationView;

/**
 * Responsible to handle locations request to view
 * Created by cristianoliveira on 22/08/15.
 */
public class LocationPresenter implements LocationListener {

    private LocationView view;
    private LocationTracker locationTracker;
    private Location currentLocation;

    public LocationPresenter(LocationView locationView, LocationTracker locationTracker) {
        this.view = locationView;
        this.locationTracker = locationTracker;
    }

    /**
     * Start receive location updates
     */
    public void startListen() {
        if(!locationTracker.startListener(this)) {
            view.showMessageLocationServiceDisabled();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        view.onUpdateLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        view.onProviderChange(s);
    }

    @Override
    public void onProviderDisabled(String s) {
        view.onProviderChange(s);
    }

    /**
     * Get current location
     * @return Current location or Last location if it isn't updating. (Can return null)
     */
    public Location getCurrentLocation() {
        if (currentLocation == null) {
            return locationTracker.getLastKnowLocation();
        }
        return currentLocation;
    }

    /**
     * Get origin based on current location
     * @return String that represent the origin point.
     */
    public String getOrigin() {
        String origin = DirectionFormatter.format(getCurrentLocation());
        return origin;
    }
}
