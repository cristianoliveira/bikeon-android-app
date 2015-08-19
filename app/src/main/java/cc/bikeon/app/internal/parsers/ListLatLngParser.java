package cc.bikeon.app.internal.parsers;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;

/**
 * Responsible for Parse a given set of coordinates to list of LatLng points
 * Created by cristianoliveira on 18/08/15.
 */
public class ListLatLngParser implements Parser<List<LatLng>, Collection<Coordinate>> {
    /**
     * Parse a given set of coordinates to list of LatLng points
     * @param coordinates
     * @return LatLng points
     */
    public List<LatLng> parse(Collection<Coordinate> coordinates) {
        List<LatLng> points = new ArrayList<LatLng>();

        for (Coordinate coordinate:coordinates) {
            points.add(
                    new LatLng(coordinate.getLatitude(), coordinate.getLongitude())
            );
        }
        return points;
    }
}
