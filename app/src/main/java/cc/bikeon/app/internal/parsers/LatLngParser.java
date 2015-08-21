package cc.bikeon.app.internal.parsers;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;

/**
 * Responsible for Parse a given set of Coordinate to LatLng
 * Created by cristianoliveira on 18/08/15.
 */
public class LatLngParser implements Parser<LatLng, Coordinate> {
    /**
     * Parse a given set of coordinates to list of LatLng points
     * @param coordinate
     * @return LatLng point
     */
    public LatLng parse(Coordinate coordinate) {
        return new LatLng(coordinate.getLatitude(), coordinate.getLongitude());
    }
}
