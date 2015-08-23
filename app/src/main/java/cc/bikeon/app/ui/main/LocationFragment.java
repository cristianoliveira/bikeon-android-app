package cc.bikeon.app.ui.main;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.R;
import cc.bikeon.app.domain.weather.Weather;
import cc.bikeon.app.internal.validators.EmptyTextViewValidation;
import cc.bikeon.app.internal.validators.Validator;
import cc.bikeon.app.internal.validators.ValidatorBuilder;
import cc.bikeon.app.presenter.LocationPresenter;
import cc.bikeon.app.presenter.factories.LocationPresenterFactory;
import cc.bikeon.app.services.rest.weather.WeatherFormatter;
import cc.bikeon.app.domain.weather.Temperature;
import cc.bikeon.app.presenter.WeatherPresenter;
import cc.bikeon.app.presenter.factories.WeatherPresenterFactory;
import cc.bikeon.app.views.LocationView;
import cc.bikeon.app.views.WeatherView;


/**
 *
 *  MAIN FRAGMENT
 *
 *  Where user select and find the route.
 *  Also is the first fragment after login
 *
 */
public class LocationFragment extends Fragment implements WeatherView, LocationView,
        View.OnClickListener {

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

    private WeatherPresenter weatherPresenter;
    private LocationPresenter locationPresenter;
    private Validator textViewValidator;

    public LocationFragment() {
        super();
    }

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

        locationPresenter = LocationPresenterFactory.createFor(this);
        weatherPresenter = WeatherPresenterFactory.createFor(this);

        btnWhereUGo.setOnClickListener(this);

        textViewValidator =
                new ValidatorBuilder<TextView>()
                              .addValidation(new EmptyTextViewValidation("Campo deve ser informado."))
                              .build();



        // TODO remove after tests
        etxWhereYouGo.setText("Porto Alegre");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        locationPresenter.startListen();
    }

    @Override
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();

        String error = textViewValidator.validate(etxWhereYouGo);

        if (error == null) {
            String destination = etxWhereYouGo.getText().toString();
            etxWhereYouGo.clearFocus();
            mainActivity.showMapNavigationFragment(destination);
        } else {
            etxWhereYouGo.setError(error);
        }
    }

    @Override
    public void setEnableRequestDirections(boolean isEnabled) {
        etxWhereYouGo.setEnabled(isEnabled);
    }

    @Override
    public void showWeather(Weather weather) {
        txtWeatherCondition.setText(weather.getDescription());
    }

    @Override
    public void showTemperature(Temperature temperature) {
        txtWeatherTemperature.setText(
                WeatherFormatter.formatCelsius(temperature.getTemp()));
        txtWeatherTemperatureMax.setText(
                WeatherFormatter.formatCelsius(temperature.getTempMax()));
        txtWeatherTemperatureMin.setText(
                WeatherFormatter.formatCelsius(temperature.getTempMin()));
    }

    @Override
    public void showMessageOnRequestError() {
        if (isResumed()) {
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.message_error_unavailable_service)
                    .show();
        }
    }

    @Override
    public void onUpdateLocation(Location location) {
        weatherPresenter.requestWeatherData(location);
    }

    @Override
    public void onProviderChange(String s) {

    }

    @Override
    public void showMessageLocationServiceDisabled() {
        if(isResumed()) {
            new AlertDialog
                    .Builder(getActivity())
                    .setMessage(R.string.message_error_location)
                    .setPositiveButton(getString(R.string.button_gps_setting_option), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent settings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(settings);
                        }
                    })
                    .setNegativeButton(getString(R.string.button_cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getActivity().finish();
                        }
                    }).show();
        }
    }

    @Override
    public void showError(int resId) {
        if (isResumed()) {
            new AlertDialog.Builder(getActivity())
                    .setMessage(resId)
                    .show();
        }
    }
}
