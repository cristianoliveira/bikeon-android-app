package cc.bikeon.app.services.rest.weather;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import cc.bikeon.app.services.rest.RestClient;
import cc.bikeon.app.services.rest.RestProvider;
import retrofit.Callback;
import retrofit.client.Response;

/**
 * Created by cristianoliveira on 30/05/15.
 */
public class WeatherServiceTest extends AndroidTestCase {

    @SmallTest
    public void testGivenLocalizationItshouldReturnWeatherData()
    {
        // given
        int timeout = 1000;
        double latitude = 20;
        double longitude = 20;
        RestProvider provider = new OpenWeatherProvider();
        RestClient restClient = new RestClient(provider);
        WeatherService service = (WeatherService) restClient.getService(WeatherService.class);
        Callback<WeatherResponse> mockedCallback =
                (Callback<WeatherResponse>)mock(Callback.class);
        ArgumentCaptor<WeatherResponse> weatherResponseArgumentCaptor =
                ArgumentCaptor.forClass(WeatherResponse.class);

        // when
        service.getWeatherByGeo(WeatherConstants.METRIC,
                WeatherConstants.LANGUAGE,
                latitude,
                longitude,
                mockedCallback);
        verify(mockedCallback, timeout(timeout)).success(weatherResponseArgumentCaptor.capture(),
                any(Response.class));
        WeatherResponse response = weatherResponseArgumentCaptor.getValue();

        // then
        assertNotNull("There are no response from weather service",response.getWeather());
        assertNotNull(response.getWeather().get(0));

    }

}
