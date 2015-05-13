package cc.bikeon.app.services.weather;

import cc.bikeon.app.services.IRestProvider;

/**
 * Created by cristianoliveira on 13/05/15.
 */
public class OpenWeatherProvider implements IRestProvider {

    private final String BASE_URL = "api.openweathermap.org";

    @Override
    public String getBaseUrl() {
        return null;
    }
}
