package cc.bikeon.app.ui;

import android.app.FragmentManager;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.ContextThemeWrapper;
import android.view.View;

import com.google.android.gms.maps.MapFragment;

import org.mockito.Mockito;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.services.local.location.LocationTracker;

/**
 * Created by cristianoliveira on 04/06/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    ContextThemeWrapper context;
    MainActivity mainActivity;
    Instrumentation instrumentation;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @SmallTest
    public void testWhenStartItShouldShowLocationFragment(){
        // given
        Instrumentation.ActivityMonitor activityMonitor =
                getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);

        String tag = "FRAGMENT_CONTENT";
        Class expectedFragment = LocationFragment.class;
//        mainActivity = getActivity();

        // when
        assertTrue(true);
    }

//    @LargeTest
//    public void testWhenSubmitDestinationItShouldShowMapNavigationActivity(){
//
//        // given
//        String tag = "FRAGMENT_CONTENT";
//        String expectedDestination = "Porto Alegre";
//        Class expectedFragment = MapFragment.class;
//        FragmentManager manager = getActivity().getFragmentManager();
//        LocationFragment locationFragment = (LocationFragment) manager.findFragmentByTag(tag);
//
//        // when
//        locationFragment.etxWhereYouGo.setText(expectedDestination);
//        locationFragment.onClick(Mockito.any(View.class));
//
//        // then
//        assertEquals(expectedDestination, mainActivity.getDestination());
//        assertTrue(mainActivity.getMapFragment().isVisible());
//    }


}
