package cc.bikeon.app.views;

import cc.bikeon.app.domain.weather.Weather;
import cc.bikeon.app.domain.weather.Temperature;

/**
 * Created by cristianoliveira on 06/07/15.
 */
public interface WeatherView {
    void setEnableRequestDirections(boolean isEnabled);
    void showWeather(Weather weather);
    void showTemperature(Temperature temperature);
    void showMessageOnRequestError();
    void showLocationRequestError();
}
