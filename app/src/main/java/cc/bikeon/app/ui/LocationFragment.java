package cc.bikeon.app.ui;

import android.app.Activity;
import android.app.ListFragment;
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

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.services.rest.RestClient;
import cc.bikeon.app.services.rest.weather.OpenWeatherProvider;
import cc.bikeon.app.services.rest.weather.WeatherResponse;
import cc.bikeon.app.services.rest.weather.WeatherService;
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
public class LocationFragment extends Fragment implements LocationListener ,Callback<WeatherResponse> {

    private OnFragmentInteractionListener mListener;

    RestClient service;
    WeatherService weatherService;

    @InjectView(R.id.txtWeatherCondition)
    TextView txtWeatherCondition;

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

        onLocationChanged(location);

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

        txtWeatherCondition.setText(weatherResponse.getWeather().get(0).getDescription());

    }

    @Override
    public void failure(RetrofitError error) {

        Toast.makeText(getActivity(), "Weather service indisponível", Toast.LENGTH_LONG);
        txtWeatherCondition.setVisibility(View.INVISIBLE);

    }

    // LOCATION SERVICE
    @Override
    public void onLocationChanged(Location location) {

        double lat = location.getLatitude();
        double lon = location.getLongitude();

        weatherService.getWeatherByGeo(lat, lon, this);
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

}
