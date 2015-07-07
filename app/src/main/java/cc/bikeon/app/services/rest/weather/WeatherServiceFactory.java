package cc.bikeon.app.services.rest.weather;

import cc.bikeon.app.services.rest.RestClient;

/**
 * Created by cristianoliveira on 06/07/15.
 */
public class WeatherServiceFactory {
    public static WeatherService createWeatherService() {
        OpenWeatherProvider provider = new OpenWeatherProvider();
        RestClient client = new RestClient(provider);
        return client.getService(WeatherService.class);
    }
}
