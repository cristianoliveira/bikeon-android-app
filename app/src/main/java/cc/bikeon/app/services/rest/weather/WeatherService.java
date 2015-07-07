package cc.bikeon.app.services.rest.weather;

import cc.bikeon.app.domain.WeatherResponse;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by cristianoliveira on 12/05/15.
 */
public interface WeatherService
{
    @GET("/data/2.5/weather")
    public void getWeather(
            @Query("lang") String language,
            @Query("q") String strCity,
            Callback<WeatherResponse> callback);

    @GET("/data/2.5/weather")
    public void getWeatherByGeo(
            @Query("units") String metric,
            @Query("lang") String language,
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            Callback<WeatherResponse> callback);

    @GET("/img/w/{icon}.png")
    public void getWeatherConditionIcon(@Path("icon") String icon, Callback<Response> callback);
}
