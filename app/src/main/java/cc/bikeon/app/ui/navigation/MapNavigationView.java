package cc.bikeon.app.ui.navigation;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by cristianoliveira on 01/07/15.
 */
public interface MapNavigationView {
    void setMapRoute(List<LatLng> points);
    void showMessageError(String message);
    void setDestination(String destination);
    void showInvalidDestinationError();
}
