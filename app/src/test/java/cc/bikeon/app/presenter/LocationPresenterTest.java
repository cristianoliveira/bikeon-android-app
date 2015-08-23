package cc.bikeon.app.presenter;

import android.location.Location;
import android.location.LocationListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cc.bikeon.app.R;
import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.views.LocationView;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link LocationPresenter}
 * Created by cristianoliveira on 22/08/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class LocationPresenterTest {

    @Mock
    LocationView locationView;
    @Mock
    LocationTracker locationTracker;
    @InjectMocks
    LocationPresenter locationPresenter;

    @Test
    public void itShouldShowMessageLocationServiceDisabledIfCanNotStartListenLocation() {
        // given
        given(locationTracker.startListener(locationPresenter)).willReturn(false);

        // when
        locationPresenter.startListen();

        // then
        verify(locationView).showMessageLocationServiceDisabled();
    }

    @Test
    public void itShouldCallOnLocationChangeWhenHasNewLocation() {
        // given
        Location expectedLocation = mock(Location.class);

        // when
        locationPresenter.onLocationChanged(expectedLocation);

        // then
        verify(locationView).onUpdateLocation(expectedLocation);
    }

    @Test
    public void itShouldReturnLastLocationWhenCurrentLocationStillDoNotUpdate() {
        // given
        Location expectedLocation = mock(Location.class);
        given(locationTracker.getLastKnowLocation()).willReturn(expectedLocation);

        // when
        Location result = locationPresenter.getCurrentLocation();

        // then
        assertEquals(expectedLocation, result);
    }

    @Test
    public void itShouldReturnCurrentLocationWhenLocationWasUpdated() {
        // given
        Location currentLocation = mock(Location.class);

        Location lastLocation = mock(Location.class);
        given(locationTracker.getLastKnowLocation()).willReturn(lastLocation);

        locationPresenter.onLocationChanged(currentLocation);

        // when
        Location result = locationPresenter.getCurrentLocation();

        // then
        assertEquals(currentLocation, result);
    }

}