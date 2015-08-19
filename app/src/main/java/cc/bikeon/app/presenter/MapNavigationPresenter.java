package cc.bikeon.app.presenter;

import com.google.android.gms.internal.ca;
import com.google.android.gms.maps.model.LatLng;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.domain.Coordinate;
import cc.bikeon.app.services.rest.directions.DirectionCallback;
import cc.bikeon.app.services.rest.directions.DirectionRequester;
import cc.bikeon.app.ui.navigation.MapNavigationView;

/**
 * Presenter that contain logic for map navigation manager
 *
 * Created by cristianoliveira on 29/06/15.
 */
public class MapNavigationPresenter implements DirectionCallback {

    private MapNavigationView view;
    private DirectionRequester directionRequester;

    public MapNavigationPresenter(MapNavigationView view, DirectionRequester directionRequester) {
        this.view = view;
        this.directionRequester = directionRequester;
    }

    public void requestDirections(String origin, String destination){
        try {
            directionRequester.request(origin, destination, this);
        } catch (UnsupportedEncodingException uenc) {
            view.showMessageError(
               BikeOnApplication.getStringResource(R.string.message_error_encode)
            );
        }
    }

    @Override
    public void onSuccess(List<Coordinate> directions) {
        List<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < directions.size(); i++) {
            points.add(
                    new LatLng(directions.get(i).getLatitude(), directions.get(i).getLongitude())
            );
        }
        view.setMapRoute(points);
    }

    @Override
    public void onFailure(String error) {
        view.showMessageError(
             BikeOnApplication.getStringResource(R.string.message_error_unavailable_service)
        );
    }


}
