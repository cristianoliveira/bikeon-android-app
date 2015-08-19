package cc.bikeon.app.internal.parsers;

import com.google.android.gms.maps.model.LatLng;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

/**
 * Tests for {@link ListLatLngParser}
 * Created by cristianoliveira on 18/08/15.
 */
public class CollectionLatLngParserTest {

    ListLatLngParser collectionLatLngParser;

    @Before
    public void setUp() {
        collectionLatLngParser = new ListLatLngParser();
    }

    @Test
    public void whenListOfCoordinatesIsEmptyItShouldReturnEmptyList() {
        // given
        List<Coordinate> coordinateList = Lists.newArrayList();

        // when
        Collection<LatLng> result = collectionLatLngParser.parse(coordinateList);

        // then
        assertThat(result.isEmpty(), is(true));
    }

    @Test
    public void whenListOfCoordinatesHasAtLeastOneItShouldReturnAListOfLatLng() {
        // given
        int latitude = 20;
        int longitude = 20;
        Coordinate coordinate = new Coordinate(latitude, longitude);
        List<Coordinate> coordinateList = Lists.newArrayList(coordinate);

        LatLng expected = new LatLng(latitude, longitude);

        // when
        Collection<LatLng> result = collectionLatLngParser.parse(coordinateList);

        // then
        assertThat(result, hasItem(expected));
    }

    @Test(expected = NullPointerException.class)
    public void whenReceiveNullItShouldRaiseNullPointerException() {
        // given
        List<Coordinate> coordinateList = null;

        // when
        Collection<LatLng> result = collectionLatLngParser.parse(coordinateList);

        // then Raise error
    }

}