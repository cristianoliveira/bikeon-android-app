package cc.bikeon.app.presenter;

import cc.bikeon.app.ui.LocationFragment;
import cc.bikeon.app.ui.navigation.MapNavigationFragment;

/**
 * Created by cristianoliveira on 30/06/15.
 */
public class MainPresenter {

    private LocationFragment locationFragment;
    private MapNavigationFragment mapFragment;

    public LocationFragment getLocationFragment() {
        if (locationFragment == null) {
            locationFragment = new LocationFragment();
        }
        return locationFragment;
    }

    public MapNavigationFragment getMapFragment(String destination) {
        if (mapFragment == null) {
            mapFragment = new MapNavigationFragment();
        }

        mapFragment.setDestination(destination);

        return mapFragment;
    }

}
