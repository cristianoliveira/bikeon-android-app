package cc.bikeon.app.services.rest.weather;

import cc.bikeon.app.services.rest.IRestProvider;

/**
 * Created by cristianoliveira on 13/05/15.
 */
public class OpenWeatherProvider implements IRestProvider {

    private final String BASE_URL = "http://api.openweathermap.org";

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }
}
