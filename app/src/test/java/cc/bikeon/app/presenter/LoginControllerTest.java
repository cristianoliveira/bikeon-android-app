package cc.bikeon.app.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import cc.bikeon.app.R;
import cc.bikeon.app.account.FacebookLoginStrategy;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.account.Session;
import cc.bikeon.app.views.LoginView;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by cristianoliveira on 30/06/15.
 */
@Config(manifest = Config.NONE)
public class LoginControllerTest {

    LoginView view;
    LoginRequester loginRequester;
    LoginPresenter loginController;

    @Before
    public void setUp() {
        view = mock(LoginView.class);
        loginRequester = mock(LoginRequester.class);
        loginController = new LoginPresenter(view, loginRequester);
    }

    @Test
    public void whenButtonLoginClickItShouldRequestLogin() {
        // given
        int viewId = R.id.btnFacebookLogin;

        // when
        loginController.requestLogin(viewId);

        // then
        verify(loginRequester).requestLogin();
    }

    @Test
    public void whenRequestLoginWithFacebookButtonClickItShouldSetFacebookStragy() {
        // given
        int viewId = R.id.btnFacebookLogin;

        // when
        loginController.requestLogin(viewId);

        // then
        verify(loginRequester).setStrategy(any(FacebookLoginStrategy.class));
    }

    @Test
    public void whenLoginRequestIsSuccessItShouldRequestViewChange() {
        // when
        loginController.onLoginSuccess(mock(Session.class));

        // then
        verify(view).gotoMainActivity();
    }

    @Test
    public void whenLoginRequestFailItShouldShowMessageOnView() {
        // given
        String expectedMessage = "Some message";

        // when
        loginController.onLoginError(expectedMessage);

        // then
        verify(view).showLoginError(expectedMessage);

    }
}
