package cc.bikeon.app.presenter;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import cc.bikeon.app.R;
import cc.bikeon.app.domain.weather.Temperature;
import cc.bikeon.app.domain.weather.Weather;
import cc.bikeon.app.domain.weather.WeatherInformation;
import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.services.rest.weather.WeatherConstants;
import cc.bikeon.app.services.rest.weather.WeatherService;
import cc.bikeon.app.views.WeatherView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Contain logic for a Weather View
 *
 * Created by cristianoliveira on 29/06/15.
 */
public class WeatherPresenter implements Presenter<WeatherView>, LocationListener, Callback<WeatherInformation> {

    private final String TAG = "WeatherPresenter";

    private WeatherView view;
    private WeatherService weatherService;

    public WeatherPresenter(WeatherView view, WeatherService weatherService) {
        this.view = view;
        this.weatherService = weatherService;
    }

    public void requestWeatherData(Location location) {
        if (location != null) {
            weatherService.getWeatherByGeo(WeatherConstants.METRIC,
                    WeatherConstants.LANGUAGE,
                    location.getLatitude(),
                    location.getLongitude(),
                    this);
            view.setEnableRequestDirections(true);
        }
    }

    @Override
    public void success(WeatherInformation weatherResponse, Response response) {
        List<Weather> weathers = weatherResponse.getWeather();
        if (weathers != null) {
            view.showWeather(weathers.get(0));
        }

        Temperature temperature = weatherResponse.getTemperature();
        if (temperature != null) {
            view.showTemperature(temperature);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        view.showMessageOnRequestError();
    }

    @Override
    public void onLocationChanged(Location location) {
        requestWeatherData(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG, "onStatusChanged " + s);
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG, "onProviderEnabled " + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG, "onProviderDisabled " + s);
    }

}
