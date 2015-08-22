package cc.bikeon.app.services.local.location;

import android.location.LocationListener;
import android.location.LocationManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link LocationTracker}
 * Created by cristianoliveira on 22/08/15.
 */
public class LocationTrackerTest {

    LocationManager mockedLocationManager;
    LocationTracker locationTracker;

    @Before
    public void setUp() {
        mockedLocationManager = mock(LocationManager.class);
        locationTracker = new LocationTracker(mockedLocationManager);
    }

    @Test
    public void itShouldNotProvideUpdateIfAnyProviderIsAvailable() {
        // given
        given(mockedLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                .willReturn(false);
        given(mockedLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                .willReturn(false);

        LocationListener listener = mock(LocationListener.class);

        // when
        locationTracker.startListener(listener);

        // then
        verify(mockedLocationManager, never()).requestLocationUpdates(LocationManager.GPS_PROVIDER
                , anyInt()
                , anyInt()
                , listener);
    }

    @Test
    public void itShouldProvideUpdatesByGPSIfAtLeastGPSProviderIsUnavailable() {
        // given
        given(mockedLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                .willReturn(true);
        given(mockedLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                .willReturn(false);

        LocationListener listener = mock(LocationListener.class);

        // when
        locationTracker.startListener(listener);

        // then
        verify(mockedLocationManager).requestLocationUpdates(LocationManager.GPS_PROVIDER
                , anyInt()
                , anyInt()
                , listener);
    }

    @Test
    public void itShouldProvideUpdatesByNetworkIfAtLeastNetworkProviderIsUnavailable() {
        // given
        given(mockedLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                .willReturn(false);
        given(mockedLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                .willReturn(true);

        LocationListener listener = mock(LocationListener.class);

        // when
        locationTracker.startListener(listener);

        // then
        verify(mockedLocationManager)
                .requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        anyInt(),
                        anyInt(),
                        listener);
    }

}