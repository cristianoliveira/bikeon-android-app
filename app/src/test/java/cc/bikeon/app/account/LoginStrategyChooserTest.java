package cc.bikeon.app.account;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;

import cc.bikeon.app.account.requesters.BikeOnLoginRequester;
import cc.bikeon.app.account.requesters.FacebookLoginRequester;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link LoginStrategyChooser}
 * Created by cristianoliveira on 05/09/15.
 */
public class LoginStrategyChooserTest {

    LoginStrategyChooser loginStrategyChooser;

    @Before
    public void setUp() {
        loginStrategyChooser = new LoginStrategyChooser();
    }

    @Test
    public void itShouldReturnDefaultRequesterWhenStrategyIsNull() {
        // given
        LoginStrategy strategy = null;
        Activity activity = mock(Activity.class);

        // when
        LoginRequester requester = loginStrategyChooser.get(strategy, activity);

        // then
        assertTrue(requester instanceof BikeOnLoginRequester);
    }

    @Test
    public void itShouldReturnFacebookRequesterWhenStrategyIsFACEBOOK() {
        // given
        LoginStrategy strategy = LoginStrategy.FACEBOOK;
        Activity activity = mock(Activity.class);

        // when
        LoginRequester requester = loginStrategyChooser.get(strategy, activity);

        // then
        assertTrue(requester instanceof FacebookLoginRequester);
    }
}