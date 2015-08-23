package cc.bikeon.app.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowLocationManager;

import cc.bikeon.app.BaseRoboletricTestRunner;
import cc.bikeon.app.BuildConfig;
import cc.bikeon.app.R;
import cc.bikeon.app.presenter.WelcomePresenter;
import cc.bikeon.app.ui.login.LoginActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Tests for {@link WelcomeActivity}
 * Created by cristianoliveira on 23/08/15.
 */
@RunWith(BaseRoboletricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class WelcomeActivityTest {

    WelcomeActivity welcomeActivity;
    ShadowLocationManager shadowLocationManager;


    @Before
    public void setUp() {

        LocationManager locationManager =
                (LocationManager) RuntimeEnvironment.application.getSystemService(Context.LOCATION_SERVICE);
        shadowLocationManager = shadowOf(locationManager);

    }

    @Test
    public void whenLocationIsEnabledItShouldGoToLogin() throws Exception {
        // given
        String expectedActivity = LoginActivity.class.getName();
        shadowLocationManager.setProviderEnabled(LocationManager.GPS_PROVIDER, true);
        welcomeActivity = Robolectric.setupActivity(WelcomeActivity.class);

        ShadowActivity shadowActivity = shadowOf(welcomeActivity);

        // when
        welcomeActivity.onResume();

        Intent startedIntent = shadowActivity.getNextStartedActivity();
        String result = startedIntent.getComponent().getClassName();

        // then
        assertEquals(expectedActivity, result);
    }

    @Test
    public void whenLocationIsEnabledItShouldFinish() throws Exception {
        // given
        String expectedActivity = LoginActivity.class.getName();
        shadowLocationManager.setProviderEnabled(LocationManager.GPS_PROVIDER, true);
        welcomeActivity = Robolectric.setupActivity(WelcomeActivity.class);

        // when
        welcomeActivity.onResume();

        // then
        assertTrue(welcomeActivity.isFinishing());
    }

    @Test
    public void whenLocationIsDisabledItShouldShowError() throws Exception {
        // given
        shadowLocationManager.setProviderEnabled(LocationManager.GPS_PROVIDER, false);
        shadowLocationManager.setProviderEnabled(LocationManager.NETWORK_PROVIDER, false);
        welcomeActivity = Robolectric.setupActivity(WelcomeActivity.class);

        String expectedMessage = welcomeActivity.getString(R.string.message_error_location);

        // when
        welcomeActivity.onResume();

        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog shadowAlertDialog = shadowOf(dialog);

        // then
        assertEquals(expectedMessage, shadowAlertDialog.getMessage().toString());
    }

}