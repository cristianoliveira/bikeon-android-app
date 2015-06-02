package cc.bikeon.app.services.rest.directions;

import android.location.Location;
import android.location.LocationManager;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.google.gson.JsonElement;

import org.mockito.ArgumentCaptor;

import cc.bikeon.app.R;
import cc.bikeon.app.services.rest.RestClient;
import cc.bikeon.app.services.rest.RestProvider;
import retrofit.Callback;
import retrofit.client.Response;

import static org.mockito.Mockito.*;

/**
 * Created by cristianoliveira on 31/05/15.
 */
public class DirectionServiceTest extends AndroidTestCase {

    @SmallTest
    public void testGivenDestinationItShouldReturnJsonElement()
    {
        // given
        int timeout = 1000;
        double latitude = -30.1088701;
        double longitude = -51.1764553;
        String destination = "Canoas";
        String expectedStatus = "OK";

        Location mockedLocation = new Location(LocationManager.GPS_PROVIDER);
        mockedLocation.setLatitude(latitude);
        mockedLocation.setLongitude(longitude);

        RestProvider provider = new DirectionProvider();
        RestClient restClient = new RestClient(provider);
        DirectionService service = (DirectionService) restClient.getService(DirectionService.class);
        Callback<DirectionResponse> mockedCallback =
                (Callback<DirectionResponse>) mock(Callback.class);
        ArgumentCaptor<DirectionResponse> weatherResponseArgumentCaptor =
                ArgumentCaptor.forClass(DirectionResponse.class);

        // when
        service.getByDestinationName(getContext().getString(R.string.google_maps_key),
                DirectionConstants.DIRECTION_MODE,
                DirectionFormatter.fromLocation(mockedLocation),
                destination,
                mockedCallback);

        verify(mockedCallback, timeout(timeout)).success(weatherResponseArgumentCaptor.capture(),
                any(Response.class));
        DirectionResponse response = weatherResponseArgumentCaptor.getValue();

        System.out.print(service.toString());

        // then
        assertNotNull("There are no response from service", response);
        assertEquals(expectedStatus, response.getStatus());
        assertNotNull(response.getRoutes());

    }

}
