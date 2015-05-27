package cc.bikeon.app.ui;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.services.rest.RestClient;
import cc.bikeon.app.services.rest.weather.OpenWeatherProvider;
import cc.bikeon.app.services.rest.weather.Weather;
import cc.bikeon.app.services.rest.weather.WeatherConstants;
import cc.bikeon.app.services.rest.weather.WeatherFormatter;
import cc.bikeon.app.services.rest.weather.WeatherResponse;
import cc.bikeon.app.services.rest.weather.WeatherService;
import cc.bikeon.app.services.rest.weather.WeatherTemperature;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 *
 *  MAIN FRAGMENT
 *
 *  Where user select and find the route.
 *  Also is the first fragment after login
 *
 */
public class LocationFragment extends Fragment implements LocationListener , Callback<WeatherResponse>{

    private OnFragmentInteractionListener mListener;

    RestClient service;
    WeatherService weatherService;

    @InjectView(R.id.txtWeatherCondition)
    TextView txtWeatherCondition;
    @InjectView(R.id.txtWeatherTemperature)
    TextView txtWeatherTemperature;
    @InjectView(R.id.txtWeatherTemperatureMin)
    TextView txtWeatherTemperatureMin;
    @InjectView(R.id.txtWeatherTemperatureMax)
    TextView txtWeatherTemperatureMax;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.inject(this, view);

        BikeOnApplication application = (BikeOnApplication) getActivity().getApplication();

        service = new RestClient(new OpenWeatherProvider());
        weatherService = (WeatherService) service.getService(WeatherService.class);

        Location location = application.getLocationTracker().getLastKnowLocation();

        if(location!= null)
        {
            onLocationChanged(location);
        }

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // WEATHER SERVICE
    @Override
    public void success(WeatherResponse weatherResponse, Response response) {

        if(!response.getUrl().contains("png")){
            setWeatherInfo(weatherResponse);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(getActivity(),
                R.string.message_error_unavailable_service,
                Toast.LENGTH_LONG);
    }

    // LOCATION SERVICE
    @Override
    public void onLocationChanged(Location location) {
        weatherService.getWeatherByGeo(WeatherConstants.METRIC,
                WeatherConstants.LANGUAGE,
                location.getLatitude(),
                location.getLongitude(),
                this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    /**
     *
     *  Each activity that hold this Fragment must implement it.
     *
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    private void setWeatherInfo(WeatherResponse weatherResponse)
    {
        List<Weather> weathers = weatherResponse.getWeather();
        if(weathers!= null){
            txtWeatherCondition.setText(weathers.get(0).getDescription());
        }

        WeatherTemperature temperature = weatherResponse.getTemperature();
        if(temperature!=null) {
            txtWeatherTemperature.setText(
                    WeatherFormatter.formatCelsius(temperature.getTemp()));
            txtWeatherTemperatureMax.setText(
                    WeatherFormatter.formatCelsius(temperature.getTempMax()));
            txtWeatherTemperatureMin.setText(
                    WeatherFormatter.formatCelsius(temperature.getTempMin()));
        }
    }

}
