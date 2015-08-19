package cc.bikeon.app.ui.main;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.R;
import cc.bikeon.app.domain.Weather;
import cc.bikeon.app.internal.validator.EmptyTextViewValidation;
import cc.bikeon.app.internal.validator.TextViewValidator;
import cc.bikeon.app.services.rest.weather.WeatherFormatter;
import cc.bikeon.app.domain.WeatherTemperature;
import cc.bikeon.app.presenter.WeatherPresenter;
import cc.bikeon.app.presenter.factories.WeatherPresenterFactory;
import cc.bikeon.app.ui.weather.WeatherView;


/**
 *
 *  MAIN FRAGMENT
 *
 *  Where user select and find the route.
 *  Also is the first fragment after login
 *
 */
public class LocationFragment extends Fragment implements WeatherView,
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

    private WeatherPresenter presenter;
    private TextViewValidator textViewValidator;

    public LocationFragment() {
        super();
    }

    @VisibleForTesting
    protected WeatherPresenter getWeatherPresenter() {
        if (presenter == null) {
            presenter = WeatherPresenterFactory.createFor(this);
        }
        return presenter;
    }

    public void setPresenter(WeatherPresenter presenter) {
        this.presenter = presenter;
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

        getWeatherPresenter().requestWeatherData(null);

        btnWhereUGo.setOnClickListener(this);

        textViewValidator =
                new TextViewValidator.Builder()
                              .addValidation(new EmptyTextViewValidation("Campo deve ser informado."))
                              .build();

        return view;
    }

    @Override
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();

        String error = textViewValidator.validate(etxWhereYouGo);

        if (error == null) {
            String destination = etxWhereYouGo.getText().toString();
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
    public void showTemperature(WeatherTemperature temperature) {
        txtWeatherTemperature.setText(
                WeatherFormatter.formatCelsius(temperature.getTemp()));
        txtWeatherTemperatureMax.setText(
                WeatherFormatter.formatCelsius(temperature.getTempMax()));
        txtWeatherTemperatureMin.setText(
                WeatherFormatter.formatCelsius(temperature.getTempMin()));
    }

    @Override
    public void showMessageOnRequestError() {
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.message_error_unavailable_service)
                .show();
    }

    @Override
    public void showLocationRequestError() {
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.message_error_location)
                .show();
    }
}
