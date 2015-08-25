package cc.bikeon.app.presenter;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.annotation.Config;

import cc.bikeon.app.R;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.views.LoginView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link LoginPresenter}
 * Created by cristianoliveira on 30/06/15.
 */
@Config(manifest = Config.NONE)
public class LoginPresenterTest {

    LoginView view;
    LoginPresenter loginPresenter;

    @Before
    public void setUp() {
        view = mock(LoginView.class);
        loginPresenter = new LoginPresenter(view);
    }

    @Test
    public void itShouldRequestLoginWithAGivenStrategyUsingItSelfAsCallback() {
        // given
        LoginRequester loginRequester = mock(LoginRequester.class);

        // when
        loginPresenter.requestLogin(loginRequester);

        // then
        verify(loginRequester).doLogin(loginPresenter);
    }

    @Test
    public void whenLoginRequestIsSuccessItShouldRequestViewChange() {
        // when
        loginPresenter.onLoginSuccess(null);

        // then
        verify(view).gotoMainActivity();
    }

    @Test
    public void whenLoginRequestFailItShouldShowMessageOnView() {
        // given
        String someError = "Some message error";

        // when
        loginPresenter.onLoginError(someError);

        // then
        verify(view).showError(R.string.message_error_login);

    }
}
