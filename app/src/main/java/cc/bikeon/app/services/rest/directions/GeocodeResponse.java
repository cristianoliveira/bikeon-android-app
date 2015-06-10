package cc.bikeon.app.services.rest.directions;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import cc.bikeon.app.services.rest.RestResponse;

/**
 *
 *  Represent Geocode response standard interface
 *
 *  Created by cristianoliveira on 06/06/15.
 */
public interface GeocodeResponse extends RestResponse<List<LatLng>> {

}
