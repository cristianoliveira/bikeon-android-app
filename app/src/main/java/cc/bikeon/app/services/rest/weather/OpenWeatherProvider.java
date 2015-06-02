package cc.bikeon.app.services.rest.weather;

import cc.bikeon.app.services.rest.RestProvider;

/**
 * Created by cristianoliveira on 13/05/15.
 */
public class OpenWeatherProvider implements RestProvider {

    @Override
    public String getBaseUrl() {
        return WeatherConstants.OPEN_WEATHER_URL_PROVIDER;
    }

}
