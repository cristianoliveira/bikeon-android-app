package cc.bikeon.app.presenter;

import android.location.Location;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import cc.bikeon.app.R;
import cc.bikeon.app.domain.weather.Temperature;
import cc.bikeon.app.domain.weather.Weather;
import cc.bikeon.app.domain.weather.WeatherInformation;
import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.services.rest.weather.WeatherConstants;
import cc.bikeon.app.services.rest.weather.WeatherService;
import cc.bikeon.app.views.WeatherView;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link WeatherPresenter}
 * Created by cristianoliveira on 06/07/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTest {

    @Mock
    WeatherView view;
    @Mock
    WeatherService weatherService;
    @Mock
    LocationTracker locationTracker;
    @InjectMocks
    WeatherPresenter weatherPresenter;

    @Test
    public void whenRequestWeatherDataWithNullLocationItShouldNotRequestWeatherData() {
        // given
        Location location = null;

        // when
        weatherPresenter.requestWeatherData(location);

        // then
        verify(weatherService, never())
                .getWeatherByGeo(anyString(),
                        anyString(),
                        anyDouble(),
                        anyDouble(),
                        any(WeatherPresenter.class));
    }

    @Test
    public void whenRequestWeatherDataProvidingLocationItShouldRequestFromServiceByLocation() {
        // given
        Location expectedLocation = createStubLocation();

        // when
        weatherPresenter.requestWeatherData(expectedLocation);

        // then
        verify(weatherService).getWeatherByGeo(WeatherConstants.METRIC,
                WeatherConstants.LANGUAGE,
                expectedLocation.getLatitude(),
                expectedLocation.getLongitude(),
                weatherPresenter);
    }

    @Test
    public void whenLocationChangeItShouldRequestNewDataFromServiceWithNewLocation() {
        // given
        double expectedLatitude = 0.2;
        double expectedLongitude = 0.5;
        Location stubNewLocation = mock(Location.class);
        when(stubNewLocation.getLongitude()).thenReturn(expectedLongitude);
        when(stubNewLocation.getLatitude()).thenReturn(expectedLatitude);

        // when
        weatherPresenter.onLocationChanged(stubNewLocation);

        // then
        verify(weatherService).getWeatherByGeo(WeatherConstants.METRIC,
                WeatherConstants.LANGUAGE,
                expectedLatitude,
                expectedLongitude,
                weatherPresenter);
    }

    @Test
    public void whenLocationChangeItShouldEnableViewToRequestDirections() {
        // given
        Location stubNewLocation = createStubLocation();

        // when
        weatherPresenter.onLocationChanged(stubNewLocation);

        // then
        verify(view).setEnableRequestDirections(true);
    }

    @Test
    public void whenWeatherRequestFailsItShouldShowErrorOnView() {
        // given
        RetrofitError stubRestError = mock(RetrofitError.class);

        // when
        weatherPresenter.failure(stubRestError);

        // then
        verify(view).showMessageOnRequestError();
    }

    @Test
    public void whenWeatherDataRequestSuccessItShouldUpdateViewWeather() {
        // given
        Weather expectedWeather = new Weather();

        ArrayList<Weather> listWeather = new ArrayList<Weather>();
        listWeather.add(expectedWeather);

        Temperature weatherTemperature = new Temperature();

        WeatherInformation stubWeatherResponse = mock(WeatherInformation.class);
        when(stubWeatherResponse.getWeather()).thenReturn(listWeather);

        // when
        weatherPresenter.success(stubWeatherResponse, getStubRestResponse());

        // then
        verify(view).showWeather(expectedWeather);
    }

    @Test
    public void whenWeatherDataRequestSuccessItShouldUpdateViewTemperature() {
        // given
        Temperature expectedTemperature = new Temperature();
        Weather expectedWeather = new Weather();
        ArrayList<Weather> listWeather = new ArrayList<Weather>();
        listWeather.add(expectedWeather);
        WeatherInformation stubWeatherResponse = mock(WeatherInformation.class);
        when(stubWeatherResponse.getWeather()).thenReturn(listWeather);
        when(stubWeatherResponse.getTemperature()).thenReturn(expectedTemperature);

        // when
        weatherPresenter.success(stubWeatherResponse, getStubRestResponse());

        // then
        verify(view).showTemperature(expectedTemperature);
    }


    private Location createStubLocation() {
        double stubLat = 0.2;
        double stubLng = 0.5;
        Location stubNewLocation = mock(Location.class);
        when(stubNewLocation.getLongitude()).thenReturn(stubLng);
        when(stubNewLocation.getLatitude()).thenReturn(stubLat);
        return stubNewLocation;
    }

    private Response getStubRestResponse() {
        return new Response("http://someurl.com", 200, "a", new ArrayList<Header>(), null);
    }
}
