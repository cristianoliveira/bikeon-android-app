package cc.bikeon.app.presenter;

import android.app.Activity;
import android.content.Intent;

import cc.bikeon.app.R;
import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.account.LoginStrategy;
import cc.bikeon.app.account.LoginStrategyChooser;
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
    private LoginRequester loginRequester;

    public LoginPresenter(LoginView view, SessionManager sessionManager) {
        this.view = view;
        this.sessionManager = sessionManager;
    }

    public void verifySession() {
        SessionAccount sessionAccount = sessionManager.getCurrentSession();

        if (sessionAccount.isActive()) {
            view.gotoMainActivity();
        }
    }

    /**
     * With a given login strategy it do request login.
     * @param loginStrategyChooser The object responsible to return the requester.
     * @param loginStrategy The strategy of auth.
     * @param activity Current Activity
     */
    public void requestLogin(LoginStrategyChooser loginStrategyChooser, LoginStrategy loginStrategy,
                             Activity activity) {
        loginRequester = loginStrategyChooser.get(loginStrategy, activity);
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

    public LoginStrategyChooser getStrategyChooser() {
        return new LoginStrategyChooser();
    }

    public void onResult(int requestCode, int resultCode, Intent data) {
        loginRequester.onActivityResult(requestCode, resultCode, data);
    }
}