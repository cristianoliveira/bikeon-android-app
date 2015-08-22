package cc.bikeon.app.presenter;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.domain.directions.Coordinate;
import cc.bikeon.app.services.rest.directions.DirectionCallback;
import cc.bikeon.app.services.rest.directions.DirectionRequester;
import cc.bikeon.app.views.MapNavigationView;

/**
 * Presenter that contain logic for map navigation manager
 * <p/>
 * Created by cristianoliveira on 29/06/15.
 */
public class MapNavigationPresenter implements DirectionCallback {

    private MapNavigationView view;
    private DirectionRequester directionRequester;
    private String origin;
    private String destination;

    public MapNavigationPresenter(MapNavigationView view, DirectionRequester directionRequester) {
        this.view = view;
        this.directionRequester = directionRequester;
    }

    public void requestDirections() {
        try {
            directionRequester.request(origin, destination, this);
        } catch (UnsupportedEncodingException uenc) {
            view.showMessageError(R.string.message_error_encode);
        }
    }

    @Override
    public void onSuccess(List<Coordinate> directions) {
        if (!directions.isEmpty())
            view.setMapRoute(directions);
    }

    @Override
    public void onFailure(String error) {
        view.showMessageError(R.string.message_error_unavailable_service);
    }

    public void setDirections(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }
}
