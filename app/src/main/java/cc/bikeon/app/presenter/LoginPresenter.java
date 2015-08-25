package cc.bikeon.app.presenter;

import cc.bikeon.app.R;
import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.views.LoginView;

/**
 * Responsible to implement logic for login
 * <p/>
 * Created by cristianoliveira on 30/06/15.
 */
public class LoginPresenter implements LoginCallback {

    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void requestLogin(LoginRequester loginRequester) {
        loginRequester.doLogin(this);
    }

    @Override
    public void onLoginSuccess(Object SessionType) {
        view.gotoMainActivity();
    }

    @Override
    public void onLoginError(String messageError) {
        view.showError(R.string.message_error_login);
    }

    public void validateSession() {

        onLoginSuccess(null);
    }
}
