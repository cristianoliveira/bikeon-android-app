package cc.bikeon.app.views;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;

/**
 * Created by cristianoliveira on 01/07/15.
 */
public interface MapNavigationView {
    void setMapRoute(List<Coordinate> points);
    void showMessageError(String message);
    void setDestination(String destination);
    void showInvalidDestinationError();
}
