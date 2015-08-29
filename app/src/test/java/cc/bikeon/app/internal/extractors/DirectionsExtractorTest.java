package cc.bikeon.app.internal.extractors;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;
import cc.bikeon.app.domain.directions.GoogleDirection;
import cc.bikeon.app.domain.directions.Leg;
import cc.bikeon.app.domain.directions.Route;
import cc.bikeon.app.domain.directions.Step;
import cc.bikeon.app.internal.decoder.PolylinePointDecoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link DirectionsExtractor}
 * Created by cristianoliveira on 21/08/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class DirectionsExtractorTest {

    @Mock
    PolylinePointDecoder polylinePointDecoder;
    @InjectMocks
    DirectionsExtractor directionsExtractor;

    @Test(expected = NullPointerException.class)
    public void whenReceiveNullDirectionItShouldRaiseNullPointerException() {
        // given
        List<Route> googleDirection = null;

        // when
        List<Coordinate> result = directionsExtractor.extract(googleDirection);
    }

    @Test
    public void whenDirectionHasNoRouteItShouldReturnEmptyList() {
        // given
        List<Route> googleDirection = Lists.newArrayList();

        // when
        List<Coordinate> result = directionsExtractor.extract(googleDirection);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenRouteHasNoLegsItShouldReturnEmptyList() {
        // given
        List<Route> googleDirection = Lists.newArrayList();
        Route route = mock(Route.class);
        given(route.getLegs()).willReturn(new Leg[0]);

        googleDirection.add(route);

        // when
        List<Coordinate> result = directionsExtractor.extract(googleDirection);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenLegsHasNoStepsItShouldReturnEmptyList() {
        // given
        List<Route> googleDirection = Lists.newArrayList();
        Leg leg = mock(Leg.class);
        given(leg.getSteps()).willReturn(new Step[0]);

        Route route = mock(Route.class);
        given(route.getLegs()).willReturn(new Leg[]{leg});

        googleDirection.add(route);

        // when
        List<Coordinate> result = directionsExtractor.extract(googleDirection);

        // then
        assertTrue(result.isEmpty());
    }


    @Test
    public void whenDirectionHasStepsItShouldReturnTheStartAndEndPointAsListOfCoordinates() {
        // given
        Coordinate firstPoint = new Coordinate(1, 1);
        Coordinate secondPoint = new Coordinate(2, 2);
        Coordinate thirdPoint = new Coordinate(3, 3);
        Coordinate endPoint = new Coordinate(4, 4);

        List<Coordinate> expected =
                Lists.newArrayList(firstPoint, secondPoint, thirdPoint, endPoint);

        List<Route> googleDirection = Lists.newArrayList();

        Step firstStep = mock(Step.class);
        given(firstStep.getStartPoint()).willReturn(firstPoint);
        given(firstStep.getEndPoint()).willReturn(secondPoint);

        Step secondStep = mock(Step.class);
        given(secondStep.getStartPoint()).willReturn(thirdPoint);
        given(secondStep.getEndPoint()).willReturn(endPoint);

        Leg leg = mock(Leg.class);
        given(leg.getSteps()).willReturn(new Step[]{firstStep, secondStep});

        Route route = mock(Route.class);
        given(route.getLegs()).willReturn(new Leg[]{leg});

        googleDirection.add(route);

        // when
        List<Coordinate> result = directionsExtractor.extract(googleDirection);

        // then
        assertEquals(expected, result);
    }


}