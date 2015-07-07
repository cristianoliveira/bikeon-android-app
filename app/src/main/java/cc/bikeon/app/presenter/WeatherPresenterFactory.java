package cc.bikeon.app.presenter;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.services.rest.weather.WeatherService;
import cc.bikeon.app.services.rest.weather.WeatherServiceFactory;
import cc.bikeon.app.ui.weather.WeatherView;

/**
 * Created by cristianoliveira on 06/07/15.
 */
public class WeatherPresenterFactory {
    public static WeatherPresenter createFor(WeatherView view) {
        BikeOnApplication application = BikeOnApplication.getInstance();
        WeatherService service = WeatherServiceFactory.createWeatherService();
        return new WeatherPresenter(view, service, application.getLocationTracker());
    }
}
