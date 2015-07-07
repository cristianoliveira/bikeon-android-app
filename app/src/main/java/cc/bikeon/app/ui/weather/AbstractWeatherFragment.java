package cc.bikeon.app.ui.weather;

import android.app.Fragment;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import cc.bikeon.app.R;
import cc.bikeon.app.services.rest.RestClient;
import cc.bikeon.app.services.rest.weather.OpenWeatherProvider;
import cc.bikeon.app.services.rest.weather.WeatherConstants;
import cc.bikeon.app.domain.WeatherResponse;
import cc.bikeon.app.services.rest.weather.WeatherService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 *
 *   Abstract Weather Fragment
 *
 *   Provide all the logic to retrieve weather data from service
 *
 * Created by cristianoliveira on 28/05/15.
 */
public abstract class AbstractWeatherFragment extends Fragment
        implements Callback<WeatherResponse>, LocationListener {

    private final String TAG = "AbstractWeatherFragment";

    private RestClient service;
    private WeatherService weatherService;

    public AbstractWeatherFragment() {
        service = new RestClient(new OpenWeatherProvider());
        weatherService = (WeatherService) service.getService(WeatherService.class);
    }

    // Weather Service
    public abstract void onWeatherResultSuccess(WeatherResponse weatherResponse, Response response);

    @Override
    public void success(WeatherResponse weatherResponse, Response response) {
        onWeatherResultSuccess(weatherResponse, response);
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(getActivity(),
                R.string.message_error_unavailable_service,
                Toast.LENGTH_LONG).show();
    }

    // Location Service
    @Override
    public void onLocationChanged(Location location) {
        if (location != null){
            weatherService.getWeatherByGeo(WeatherConstants.METRIC,
                    WeatherConstants.LANGUAGE,
                    location.getLatitude(),
                    location.getLongitude(),
                    this);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG, "onStatusChanged "+s);
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG, "onProviderEnabled "+s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG, "onProviderDisabled "+s);
    }

}
