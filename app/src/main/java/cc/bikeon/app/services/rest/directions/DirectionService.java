package cc.bikeon.app.services.rest.directions;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by cristianoliveira on 31/05/15.
 */
public interface DirectionService {

    @GET("/maps/api/directions/json")
    public void getByDestinationName(
            @Query("key") String key,
            @Query("mode") String mode,
            @Query("origin") String origin,
            @Query("destination") String destination,
            Callback<DirectionResponse> callback);
}