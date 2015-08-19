package cc.bikeon.app.services.rest.directions.google;

import cc.bikeon.app.domain.directions.GoogleDirection;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Google Directions API Service interface
 *
 * Created by cristianoliveira on 31/05/15.
 */
public interface GoogleDirectionService {

    @GET("/maps/api/directions/json")
    public void getByDestinationName(
            @Query("key") String key,
            @Query("mode") String mode,
            @Query("origin") String origin,
            @Query("destination") String destination,
            Callback<GoogleDirection> callback);
}
