package cc.bikeon.app.presenter.factories;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.presenter.MapNavigationPresenter;
import cc.bikeon.app.services.rest.directions.DirectionRequester;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionRequester;
import cc.bikeon.app.ui.navigation.MapNavigationView;

/**
 * Responsible for create a new instance of {@link MapNavigationPresenter} for a given
 * {@link MapNavigationView}
 * <p/>
 * Created by cristianoliveira on 18/08/15.
 */
public class MapNavigationPresenterFactory {
    public static MapNavigationPresenter createFor(MapNavigationView view) {

        DirectionRequester requester =
                new GoogleDirectionRequester(
                        BikeOnApplication.getStringResource(R.string.google_maps_key)
                );

        MapNavigationPresenter presenter = new MapNavigationPresenter(view, requester);

        return presenter;
    }
}
