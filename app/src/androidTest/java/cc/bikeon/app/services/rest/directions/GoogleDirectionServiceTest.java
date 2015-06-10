package cc.bikeon.app.services.rest.directions;

import android.location.Location;
import android.location.LocationManager;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import org.mockito.ArgumentCaptor;

import java.io.UnsupportedEncodingException;

import cc.bikeon.app.R;
import cc.bikeon.app.services.rest.RestClient;
import cc.bikeon.app.services.rest.RestProvider;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionProvider;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionResponse;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionService;
import retrofit.Callback;
import retrofit.client.Response;

import static org.mockito.Mockito.*;

/**
 * Created by cristianoliveira on 31/05/15.
 */
public class GoogleDirectionServiceTest extends AndroidTestCase {

    @SmallTest
    public void testGivenGoogleDirectionServiceDestinationItShouldReturnGeocodeResponse() throws
            UnsupportedEncodingException {
        // given
        int timeout = 1000;
        double latitude = -30.1088701;
        double longitude = -51.1764553;
        String destination = "Canoas";
        String expectedStatus = "OK";
        String googleKey = getContext().getString(R.string.google_maps_key);

        Location mockedLocation = new Location(LocationManager.GPS_PROVIDER);
        mockedLocation.setLatitude(latitude);
        mockedLocation.setLongitude(longitude);

        RestProvider provider = new GoogleDirectionProvider();
        RestClient restClient = new RestClient(provider);
        GoogleDirectionService service = (GoogleDirectionService) restClient.getService(GoogleDirectionService.class);
        Callback<GoogleDirectionResponse> mockedCallback =
                (Callback<GoogleDirectionResponse>) mock(Callback.class);
        ArgumentCaptor<GoogleDirectionResponse> weatherResponseArgumentCaptor =
                ArgumentCaptor.forClass(GoogleDirectionResponse.class);

        // when
        service.getByDestinationName(googleKey,
                DirectionConstants.DIRECTION_MODE,
                DirectionFormatter.format(mockedLocation),
                DirectionFormatter.format(destination),
                mockedCallback);

        verify(mockedCallback, timeout(timeout)).success(weatherResponseArgumentCaptor.capture(),
                any(Response.class));

        GeocodeResponse response = weatherResponseArgumentCaptor.getValue();

        System.out.print(service.toString());

        // then
        assertNotNull("There are no response from service", response);
        assertEquals(expectedStatus, response.getStatus());
        assertTrue("Response doesn't have routes.", response.getData().size() > 0);


    }

}
