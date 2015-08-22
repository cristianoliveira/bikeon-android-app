package cc.bikeon.app.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by cristianoliveira on 06/07/15.
 */
public class LoginRequesterTest {

    @Test
    public void whenLoginRequestItShouldUseGivenStrategyAndCallback() {
        // given
        LoginStrategy loginStrategy = mock(FacebookLoginStrategy.class);
        LoginCallback loginCallback = mock(LoginCallback.class);

        LoginRequester loginRequester = new LoginRequester();
        loginRequester.setStrategy(loginStrategy);
        loginRequester.setCallback(loginCallback);

        // when
        loginRequester.requestLogin();

        // then
        verify(loginStrategy).doLogin(loginCallback);
    }

}
