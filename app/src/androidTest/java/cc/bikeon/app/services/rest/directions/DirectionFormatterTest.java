package cc.bikeon.app.services.rest.directions;

import android.location.Location;
import android.location.LocationManager;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by cristianoliveira on 02/06/15.
 */
public class DirectionFormatterTest extends TestCase {

    @Test
    public void testGivenLocationItShouldFormatToURLString()
    {
        // given
        double latitude = -30.1088701;
        double longitude = -51.1764553;
        String expected = String.format("%s,%s",latitude, longitude);

        Location mockedLocation = new Location(LocationManager.GPS_PROVIDER);
        mockedLocation.setLatitude(latitude);
        mockedLocation.setLongitude(longitude);

        // when
        String result = DirectionFormatter.fromLocation(mockedLocation);

        // then
        assertEquals(expected, result);
    }
}
