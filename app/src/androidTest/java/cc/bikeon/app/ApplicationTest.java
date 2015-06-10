package cc.bikeon.app;

import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;

import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.ui.LoginActivity;
import cc.bikeon.app.ui.WelcomeActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

//@RunWith(RobolectricTestRunner.class)
public class ApplicationTest extends ActivityUnitTestCase<WelcomeActivity> {

    public ApplicationTest(Class<WelcomeActivity> activityClass) {
        super(activityClass);
    }

    @Test
    public void testMainActivity()
    {
        WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();
        org.junit.Assert.assertNotNull(welcomeActivity);
    }

    @SmallTest
    public void testWhenRequestLocationTrackerItShouldCreateNewOne()
    {
        // given
        BikeOnApplication application = BikeOnApplication.getInstance();

        // when
        LocationTracker locationTracker = application.getLocationTracker();

        // then
        assertNotNull(locationTracker);
    }
}