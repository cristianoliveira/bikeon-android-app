package cc.bikeon.app.views;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;

/**
 * View interface for MapNavigation Presentation
 * Created by cristianoliveira on 01/07/15.
 */
public interface MapNavigationView extends View {
    void setMapRoute(List<Coordinate> points);
    void setDestination(String destination);
}
