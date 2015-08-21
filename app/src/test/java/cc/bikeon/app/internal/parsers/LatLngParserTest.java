package cc.bikeon.app.internal.parsers;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;

import cc.bikeon.app.domain.directions.Coordinate;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link LatLngParser}
 * Created by cristianoliveira on 18/08/15.
 */
public class LatLngParserTest {

    LatLngParser latLngParser;

    @Before
    public void setUp() {
        latLngParser = new LatLngParser();
    }

    @Test(expected = NullPointerException.class)
    public void whenCordinateIsNullItShouldRaiseNullPointerException() {
        // given
        Coordinate coordinate = null;

        // when
        LatLng result = latLngParser.parse(coordinate);

        // then raise NPE
    }

    @Test
    public void whenListOfCoordinatesHasAtLeastOneItShouldReturnAListOfLatLng() {
        // given
        int latitude = 20;
        int longitude = 20;
        Coordinate coordinate = new Coordinate(latitude, longitude);
        LatLng expected = new LatLng(latitude, longitude);

        // when
        LatLng result = latLngParser.parse(coordinate);

        // then
        assertEquals(expected, result);
    }

}