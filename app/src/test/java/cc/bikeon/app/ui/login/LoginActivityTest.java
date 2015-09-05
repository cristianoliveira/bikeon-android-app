package cc.bikeon.app.ui.login;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import cc.bikeon.app.BaseRoboletricTestRunner;
import cc.bikeon.app.BuildConfig;
import cc.bikeon.app.account.LoginStrategy;
import cc.bikeon.app.account.LoginStrategyChooser;
import cc.bikeon.app.account.requesters.FacebookLoginRequester;
import cc.bikeon.app.presenter.LoginPresenter;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link LoginActivity}
 * Created by cristianoliveira on 23/08/15.
 */
@RunWith(BaseRoboletricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginActivityTest {

    @Test
    public void itShouldVerifySessionOnResume() {
        // given
        LoginPresenter loginPresenter = mock(LoginPresenter.class);

        ActivityController<LoginActivity> controller = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity activity = controller.create().get();
        activity.presenter = loginPresenter;

        // when
        controller.resume();

        //then
        verify(loginPresenter).verifySession();
    }

    @Test
    public void itShouldRequestFacebookLoginWhenClickFacebookButton() {
        // given
        LoginPresenter loginPresenter = mock(LoginPresenter.class);

        ActivityController<LoginActivity> controller = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity activity = controller.create().get();
        activity.presenter = loginPresenter;
        controller.resume();

        // when
        activity.btnFacebookLogin.callOnClick();

        // then
        verify(loginPresenter).requestLogin(any(LoginStrategyChooser.class),
                eq(LoginStrategy.FACEBOOK), eq(activity));
    }

    @Test
    public void itShouldFinishWhenGoToMain() {
        // given
        LoginPresenter loginPresenter = mock(LoginPresenter.class);

        ActivityController<LoginActivity> controller = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity activity = controller.create().get();
        activity.presenter = loginPresenter;
        controller.resume();

        // when
        activity.gotoMainActivity();

        // then
        assertTrue(activity.isFinishing());
    }

}