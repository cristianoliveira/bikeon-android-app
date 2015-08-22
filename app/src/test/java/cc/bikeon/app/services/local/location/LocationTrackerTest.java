package cc.bikeon.app.services.local.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
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
        verify(mockedLocationManager, never()).requestLocationUpdates(
                eq(LocationManager.GPS_PROVIDER),
                anyLong(),
                anyFloat(),
                eq(listener));
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
        verify(mockedLocationManager).requestLocationUpdates(
                eq(LocationManager.GPS_PROVIDER),
                anyLong(),
                anyFloat(),
                eq(listener));
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
                .requestLocationUpdates(
                        eq(LocationManager.NETWORK_PROVIDER),
                        anyLong(),
                        anyFloat(),
                        eq(listener));
    }


    @Test
    public void itShouldReturnNullFromLastKnowLocationIfAnyProviderIsAvailable() {
        // given
        List<String> providers =
                Lists.newArrayList(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER);
        given(mockedLocationManager.getProviders(true))
                .willReturn(providers);
        given(mockedLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                .willReturn(false);
        given(mockedLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                .willReturn(false);

        LocationListener listener = mock(LocationListener.class);

        // when
        Location result = locationTracker.getLastKnowLocation();

        // then
        assertNull(result);
    }

}