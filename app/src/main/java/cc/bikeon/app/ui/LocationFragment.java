package cc.bikeon.app.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.services.local.location.LocationTracker;
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
public class LocationFragment extends AbstractWeatherFragment implements
        View.OnClickListener {

    private FragmentInteractionListner mListener;

    @InjectView(R.id.etxWhereYouGo)
    TextView etxWhereYouGo;
    @InjectView(R.id.btnWhereUGo)
    ImageView btnWhereUGo;

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

        BikeOnApplication application = BikeOnApplication.getInstance();
        LocationTracker locationTracker = application.getLocationTracker();
        locationTracker.startListener(this);

        Location location = locationTracker.getLastKnowLocation();

        if(location!= null)
        {
            onLocationChanged(location);
        }

        btnWhereUGo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentInteractionListner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.openMap(etxWhereYouGo.getText().toString());
    }

    @Override
    public void onWeatherResultSuccess(WeatherResponse weatherResponse, Response response) {
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
