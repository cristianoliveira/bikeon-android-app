package cc.bikeon.app.presenter;

import android.content.Context;

import cc.bikeon.app.account.session.SessionAccount;
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

    /**
     * Close current User Session
     * @param context
     */
    public void doLogout(Context context) {

        SessionManager sessionManager =
                new SessionManager(
                        context.getSharedPreferences(
                                SessionManager.SHARED_SESSION_PREFERENCE,
                                context.MODE_PRIVATE)
                );

        SessionAccount sessionAccount = sessionManager.getCurrentSession();
        sessionAccount.closeSession();

        view.onLogout();
    }
}
