package cc.bikeon.app.ui.weather;

import cc.bikeon.app.domain.Weather;
import cc.bikeon.app.domain.WeatherTemperature;

/**
 * Created by cristianoliveira on 06/07/15.
 */
public interface WeatherView {
    void setEnableRequestDirections(boolean isEnabled);
    void showWeather(Weather weather);
    void showTemperature(WeatherTemperature temperature);
    void showMessageOnRequestError();
}
