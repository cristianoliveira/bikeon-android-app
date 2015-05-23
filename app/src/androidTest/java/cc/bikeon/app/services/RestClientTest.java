package cc.bikeon.app.services;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import org.junit.Test;
import cc.bikeon.app.services.weather.WeatherService;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by cristianoliveira on 12/05/15.
 */
public class RestClientTest extends AndroidTestCase {

    @SmallTest
    public void testWhenRequestARestApiItShouldReturnService()
    {
        // given
        IRestProvider provider = new StubProvider();
        RestClient restClient = new RestClient(provider);

        // when
        WeatherService service = (WeatherService) restClient.getService(WeatherService.class);

        // then
        assertNotNull(service);
    }

}
