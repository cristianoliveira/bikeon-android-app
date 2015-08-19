package cc.bikeon.app.presenter;

import android.location.Location;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import cc.bikeon.app.domain.Weather;
import cc.bikeon.app.domain.WeatherResponse;
import cc.bikeon.app.domain.WeatherTemperature;
import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.services.rest.weather.WeatherConstants;
import cc.bikeon.app.services.rest.weather.WeatherService;
import cc.bikeon.app.ui.weather.WeatherView;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link WeatherPresenter}
 * Created by cristianoliveira on 06/07/15.
 */
@RunWith(RobolectricTestRunner.class)
public class WeatherPresenterTest {

    @Mock WeatherView view;
    @Mock WeatherService weatherService;
    @Mock LocationTracker locationTracker;
    @InjectMocks WeatherPresenter weatherPresenter;

    @Before
    public void setUp() {
        view = mock(WeatherView.class);
        weatherService = mock(WeatherService.class);
        locationTracker = mock(LocationTracker.class);
        weatherPresenter = new WeatherPresenter(view, weatherService, locationTracker);
    }

    @Test
    public void whenCreateNewWeatherPresenterItShouldStartToListnenLocation() {
        // given
        LocationTracker mockedLocationTracker = mock(LocationTracker.class);

        // when
        weatherPresenter = new WeatherPresenter(view, weatherService, mockedLocationTracker);

        // then
        verify(mockedLocationTracker).startListener(weatherPresenter);
    }

    @Test
    public void whenRequestLocationItshouldRetrieveLastKnowLocation() {
        // given
        Location expectedLocation = createStubLocation();
        when(locationTracker.getLastKnowLocation()).thenReturn(expectedLocation);

        // when
        Location result = weatherPresenter.getLastLocation();

        // then
        assertEquals(expectedLocation, result);
    }

    @Test
    public void whenRequestWeatherDataItShouldUseLastKnowLocationAsDefault() {
        // given
        Location expectedLocation = createStubLocation();

        when(locationTracker.getLastKnowLocation()).thenReturn(expectedLocation);

        // when
        weatherPresenter.requestWeatherData(null);

        // then
        verify(weatherService).getWeatherByGeo(WeatherConstants.METRIC,
                WeatherConstants.LANGUAGE,
                expectedLocation.getLatitude(),
                expectedLocation.getLongitude(),
                weatherPresenter);
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
    public void whenLastKnowLocationIsNullItShouldRequestViewToShowError() {
        // given
        when(locationTracker.getLastKnowLocation()).thenReturn(null);

        // when
        weatherPresenter.requestWeatherData(null);

        // then
        verify(view).showLocationRequestError();

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

        WeatherTemperature weatherTemperature = new WeatherTemperature();

        WeatherResponse stubWeatherResponse = mock(WeatherResponse.class);
        when(stubWeatherResponse.getWeather()).thenReturn(listWeather);

        // when
        weatherPresenter.success(stubWeatherResponse, getStubRestResponse());

        // then
        verify(view).showWeather(expectedWeather);
    }

    @Test
    public void whenWeatherDataRequestSuccessItShouldUpdateViewTemperature() {
        // given
        WeatherTemperature expectedTemperature = new WeatherTemperature();
        Weather expectedWeather = new Weather();
        ArrayList<Weather> listWeather = new ArrayList<Weather>();
        listWeather.add(expectedWeather);
        WeatherResponse stubWeatherResponse = mock(WeatherResponse.class);
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
        return new Response("http://someurl.com", 200,"a", new ArrayList<Header>(), null);
    }
}
