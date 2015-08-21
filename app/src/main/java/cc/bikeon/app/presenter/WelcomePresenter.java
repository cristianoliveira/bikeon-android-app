package cc.bikeon.app.presenter;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.ui.WelcomeView;

/**
 * Presenter responsible of manage {@link WelcomeView}
 * Created by cristianoliveira on 20/08/15.
 */
public class WelcomePresenter {

    private final WelcomeView view;

    public WelcomePresenter(WelcomeView view) {
        this.view = view;
    }

    /**
     * Verify if location is enabled.
     */
    public void verifyLocation() {

        BikeOnApplication application = BikeOnApplication.getInstance();

        LocationTracker locationTracker = application.getLocationTracker();
        boolean isServiceEnabled = locationTracker.isLocationServiceEnabled();

        application.setLocationTracker(locationTracker);

        if(isServiceEnabled){
            view.gotoLogin();

        }else {
            view.showError(R.string.message_error_location);
        }

    }


}
