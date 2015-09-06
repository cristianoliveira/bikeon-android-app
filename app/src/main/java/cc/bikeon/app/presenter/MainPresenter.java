package cc.bikeon.app.presenter;

import android.content.Context;

import cc.bikeon.app.account.session.SessionManager;
import cc.bikeon.app.ui.main.LocationFragment;
import cc.bikeon.app.ui.navigation.MapNavigationFragment;
import cc.bikeon.app.views.MainView;

/**
 * Presenter to manage MainView of Application.
 * Created by cristianoliveira on 30/06/15.
 */
public class MainPresenter {

    private LocationFragment locationFragment;
    private MapNavigationFragment mapFragment;

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public LocationFragment getLocationFragment(LocationFragment.DestinationListener selectListener) {
        if (locationFragment == null) {
            locationFragment = new LocationFragment();
            locationFragment.setDestinationListener(selectListener);
        }
        return locationFragment;
    }

    public MapNavigationFragment getMapFragment(String origin, String destination) {
        if (mapFragment == null) {
            mapFragment = new MapNavigationFragment();
        }

        mapFragment.setDestination(origin, destination);

        return mapFragment;
    }

    /**
     * Close current User Session
     * @param context
     */
    public void doLogout(Context context) {

        SessionManager sessionManager = SessionManager.Factory.create(context);
        sessionManager.closeSession();

        view.onLogout();
    }
}
