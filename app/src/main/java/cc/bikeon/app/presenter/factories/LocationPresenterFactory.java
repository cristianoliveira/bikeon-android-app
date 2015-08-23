package cc.bikeon.app.presenter.factories;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.presenter.LocationPresenter;
import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.services.local.location.LocationTrakerFactory;
import cc.bikeon.app.ui.main.LocationFragment;
import cc.bikeon.app.views.LocationView;

/**
 * Factory for LocationPresenter
 * Created by cristianoliveira on 22/08/15.
 */
public class LocationPresenterFactory {

    /**
     * Create a LocationPresenter for a given View
     * @param locationView
     * @return LocationPresenter instance
     */
    public static LocationPresenter createFor(LocationView locationView) {
        BikeOnApplication application = BikeOnApplication.getInstance();

        LocationTracker locationTracker = LocationTrakerFactory.create(application);

        LocationPresenter locationPresenter =
                new LocationPresenter(locationView, locationTracker);

        return locationPresenter;
    }
}
