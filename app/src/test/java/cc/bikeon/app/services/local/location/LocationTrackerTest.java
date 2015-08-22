package cc.bikeon.app.services.local.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link LocationTracker}
 * Created by cristianoliveira on 22/08/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class LocationTrackerTest {

    @Mock
    LocationManager mockedLocationManager;
    @InjectMocks
    LocationTracker locationTracker;

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
    public void itShouldProvideUpdatesIfAtLeastGPSProviderIsAvailable() {
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
    public void itShouldProvideUpdatesIfAtLeastNetworkProviderIsAvailable() {
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

    @Test
    public void itShouldReturnLocationFromLastKnowLocationIfAtLeastGPSProviderIsAvailable() {
        // given
        Location expected = mock(Location.class);

        List<String> providers =
                Lists.newArrayList(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER);
        given(mockedLocationManager.getProviders(true))
                .willReturn(providers);
        given(mockedLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                .willReturn(true);
        given(mockedLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                .willReturn(false);

        given(mockedLocationManager.getLastKnownLocation(anyString()))
                .willReturn(expected);

        LocationListener listener = mock(LocationListener.class);

        // when
        Location result = locationTracker.getLastKnowLocation();

        // then
        assertEquals(expected, result);
    }

    @Test
    public void itShouldReturnLocationFromLastKnowLocationIfAtLeastNetworkProviderIsAvailable() {
        // given
        Location expected = mock(Location.class);

        List<String> providers =
                Lists.newArrayList(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER);
        given(mockedLocationManager.getProviders(true))
                .willReturn(providers);
        given(mockedLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                .willReturn(false);
        given(mockedLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
                .willReturn(true);

        given(mockedLocationManager.getLastKnownLocation(anyString()))
                .willReturn(expected);

        LocationListener listener = mock(LocationListener.class);

        // when
        Location result = locationTracker.getLastKnowLocation();

        // then
        assertEquals(expected, result);
    }

}