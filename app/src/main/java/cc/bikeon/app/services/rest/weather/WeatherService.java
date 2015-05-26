package cc.bikeon.app.services.rest.weather;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by cristianoliveira on 12/05/15.
 */
public interface WeatherService
{
    @GET("/data/2.5/weather")
    public void getWeather(
            @Query("q") String strCity,
            Callback<WeatherResponse> callback);

    @GET("/data/2.5/weather")
    public void getWeatherByGeo(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            Callback<WeatherResponse> callback);
}
