package cc.bikeon.app.services.rest.weather;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import cc.bikeon.app.services.rest.RestProvider;

/**
 * Created by cristianoliveira on 13/05/15.
 */
public class OpenWeatherProviderTest extends AndroidTestCase {

    @SmallTest
    public void testWhenRequestUrlItShouldNotNull(){
        // given
        RestProvider provider = new OpenWeatherProvider();

        // when
        String result = provider.getBaseUrl();

        // then
        Assert.assertNotNull(result);
    }
}
