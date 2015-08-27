package cc.bikeon.app.presenter;

import cc.bikeon.app.R;
import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.account.session.SessionAccount;
import cc.bikeon.app.account.session.SessionManager;
import cc.bikeon.app.views.LoginView;

/**
 * Responsible to implement logic for login
 * <p/>
 * Created by cristianoliveira on 30/06/15.
 */
public class LoginPresenter implements LoginCallback {

    private LoginView view;
    private SessionManager sessionManager;

    public LoginPresenter(LoginView view, SessionManager sessionManager) {
        this.view = view;
        this.sessionManager = sessionManager;
    }

    public void verifySession() {
        SessionAccount sessionAccount = sessionManager.getCurrentSession();

        if (sessionAccount.hasSessionActive()) {
            view.gotoMainActivity();
        }
    }

    public void requestLogin(LoginRequester loginRequester) {
        loginRequester.doLogin(this);
    }

    @Override
    public void onLoginSuccess(SessionAccount session) {
        if (session != null) {
            sessionManager.saveCurrentProvider(session);
            view.gotoMainActivity();
        } else {
            view.showError(R.string.message_error_login);
        }
    }

    @Override
    public void onLoginError(String messageError) {
        view.showError(R.string.message_error_login);
    }

}
