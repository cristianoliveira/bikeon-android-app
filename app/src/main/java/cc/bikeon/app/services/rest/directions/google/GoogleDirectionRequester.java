package cc.bikeon.app.services.rest.directions.google;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cc.bikeon.app.domain.Coordinate;
import cc.bikeon.app.domain.directions.GoogleDirectionResponse;
import cc.bikeon.app.services.rest.RestServiceFactory;
import cc.bikeon.app.services.rest.directions.DirectionCallback;
import cc.bikeon.app.services.rest.directions.DirectionConstants;
import cc.bikeon.app.services.rest.directions.DirectionFormatter;
import cc.bikeon.app.services.rest.directions.DirectionRequester;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
    public void request(String origin, String destination, final DirectionCallback callback) throws UnsupportedEncodingException {
        googleDirectionService.getByDestinationName(googleDirectionKey,
                DirectionConstants.DIRECTION_MODE,
                DirectionFormatter.format(origin),
                DirectionFormatter.format(destination),
                new Callback<GoogleDirectionResponse>() {
                    @Override
                    public void success(GoogleDirectionResponse googleDirectionResponse, Response response) {
                        CoordinateParser parser = new CoordinateParser();
                        List<Coordinate> coordinates = parser.parse(googleDirectionResponse.getRoutes());
                        callback.onSuccess(coordinates);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        callback.onFailure(error.getMessage());
                    }
                }
        );
    }
}
