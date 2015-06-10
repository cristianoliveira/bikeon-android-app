package cc.bikeon.app.services.rest.directions.google;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

/**
 * Created by cristianoliveira on 06/06/15.
 */
public class GoogleMapRouter {

    /**
     *
     *  Print lines of route in a given googleMap
     *
     *  @param directions
     */
    public static void doRoute(GoogleMap googleMap, List<LatLng> directions){

        // Move the camera instantly to hamburg with a zoom of 15.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(directions.get(0), 15));

        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        PolylineOptions polynesOpt = new PolylineOptions();

        for (int i = 0; i < directions.size(); i++) {
            polynesOpt.add(directions.get(i));
        }

        googleMap.addPolyline(polynesOpt);
    }
}
