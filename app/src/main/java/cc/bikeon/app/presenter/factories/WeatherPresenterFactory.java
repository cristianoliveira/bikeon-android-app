package cc.bikeon.app.presenter.factories;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.presenter.Presenter;
import cc.bikeon.app.presenter.WeatherPresenter;
import cc.bikeon.app.services.rest.weather.WeatherService;
import cc.bikeon.app.services.rest.weather.WeatherServiceFactory;
import cc.bikeon.app.views.WeatherView;

/**
 * Responsible for create a new instance of {@link WeatherPresenter}
 * <p/>
 * Created by cristianoliveira on 06/07/15.
 */
public class WeatherPresenterFactory {
    /**
     * Create for a given {@link WeatherView}
     *
     * @param view
     * @return new instance of presenter
     */
    public static WeatherPresenter createFor(WeatherView view) {
        WeatherService service = WeatherServiceFactory.createWeatherService();
        return new WeatherPresenter(view, service);
    }
}
