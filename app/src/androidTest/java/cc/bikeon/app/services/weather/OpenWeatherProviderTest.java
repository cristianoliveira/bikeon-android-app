package cc.bikeon.app.services.weather;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;
import junit.framework.TestCase;

import cc.bikeon.app.services.IRestProvider;
import cc.bikeon.app.services.weather.OpenWeatherProvider;

/**
 * Created by cristianoliveira on 13/05/15.
 */
public class OpenWeatherProviderTest extends AndroidTestCase {

    @SmallTest
    public void testWhenRequestUrlItShouldNotNull(){
        // given
        IRestProvider provider = new OpenWeatherProvider();

        // when
        String result = provider.getBaseUrl();

        // then
        Assert.assertNotNull(result);
    }
}
