package cc.bikeon.app.services.rest.directions.google;

import android.location.Location;

import java.io.UnsupportedEncodingException;

import cc.bikeon.app.services.rest.RestServiceFactory;
import cc.bikeon.app.services.rest.directions.DirectionConstants;
import cc.bikeon.app.services.rest.directions.DirectionFormatter;
import cc.bikeon.app.services.rest.directions.DirectionRequester;
import retrofit.Callback;

/**
 * Created by cristianoliveira on 09/06/15.
 */
public class GoogleDirectionRequester implements DirectionRequester {

    private String googleDirectionKey;
    private GoogleDirectionProvider googleDirectionProvider;
    private GoogleDirectionService googleDirectionService;

    public GoogleDirectionRequester(String googleDirectionKey) {
        this.googleDirectionKey = googleDirectionKey;

        googleDirectionService =
                RestServiceFactory.create(GoogleDirectionService.class,
                        new GoogleDirectionProvider());
    }

    @Override
    public void request(String origin, String destination, Callback callback)
            throws UnsupportedEncodingException {
        googleDirectionService.getByDestinationName(googleDirectionKey,
                DirectionConstants.DIRECTION_MODE,
                DirectionFormatter.format(origin),
                DirectionFormatter.format(destination),
                callback
                );
    }

}
