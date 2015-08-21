package cc.bikeon.app.internal.extractors;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;
import cc.bikeon.app.domain.directions.GoogleDirection;
import cc.bikeon.app.domain.directions.Leg;
import cc.bikeon.app.domain.directions.Route;
import cc.bikeon.app.domain.directions.Step;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link DirectionsExtractor}
 * Created by cristianoliveira on 21/08/15.
 */
public class DirectionsExtractorTest {

    DirectionsExtractor directionsExtractor;
    List<Coordinate> emptyList = Lists.newArrayList();

    @Before
    public void setUp() {
        directionsExtractor = new DirectionsExtractor();
    }

    @Test(expected = NullPointerException.class)
    public void whenReceiveNullDirectionItShouldRaiseNullPointerException() {
        // given
        GoogleDirection googleDirection = null;

        // when
        List<Coordinate> result = directionsExtractor.extractSteps(googleDirection);
    }

    @Test
    public void whenDirectionHasNoRouteItShouldReturnEmptyList() {
        // given
        GoogleDirection googleDirection = mock(GoogleDirection.class);
        given(googleDirection.getRoutes()).willReturn(new Route[0]);

        // when
        List<Coordinate> result = directionsExtractor.extractSteps(googleDirection);

        // then
        assertEquals(emptyList, result);
    }

    @Test
    public void whenRouteHasNoLegsItShouldReturnEmptyList() {
        // given
        GoogleDirection googleDirection = mock(GoogleDirection.class);
        Route route = mock(Route.class);
        given(route.getLegs()).willReturn(new Leg[0]);

        Route[] routes = new Route[]{route};

        given(googleDirection.getRoutes()).willReturn(routes);

        // when
        List<Coordinate> result = directionsExtractor.extractSteps(googleDirection);

        // then
        assertEquals(emptyList, result);
    }

    @Test
    public void whenLegsHasNoStepsItShouldReturnEmptyList() {
        // given
        GoogleDirection googleDirection = mock(GoogleDirection.class);
        Leg leg = mock(Leg.class);
        given(leg.getSteps()).willReturn(new Step[0]);

        Route route = mock(Route.class);
        given(route.getLegs()).willReturn(new Leg[]{leg});

        Route[] routes = new Route[]{route};
        given(googleDirection.getRoutes()).willReturn(routes);

        // when
        List<Coordinate> result = directionsExtractor.extractSteps(googleDirection);

        // then
        assertEquals(emptyList, result);
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

        GoogleDirection googleDirection = mock(GoogleDirection.class);

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

        Route[] routes = new Route[]{route};
        given(googleDirection.getRoutes()).willReturn(routes);

        // when
        List<Coordinate> result = directionsExtractor.extractSteps(googleDirection);

        // then
        assertEquals(expected, result);
    }


}