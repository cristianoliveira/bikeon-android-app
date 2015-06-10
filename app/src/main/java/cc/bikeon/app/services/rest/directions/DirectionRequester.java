package cc.bikeon.app.services.rest.directions;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit.Callback;

/**
 * Created by cristianoliveira on 09/06/15.
 */
public interface DirectionRequester {

    /**
     *
     *  Request new route based on Origin to Destination. Must provide a callback to respond
     *  the rest request
     *
     * @param origin Formatted origin
     * @param destination Formatted
     * @param callback Callback will be result the request (Success/Error)
     * @throws UnsupportedEncodingException When try convert the origin/destination to UTF-8 encode
     */
    public void request(String origin, String destination, Callback callback) throws UnsupportedEncodingException;

}
