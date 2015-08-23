package cc.bikeon.app.presenter.factories;

import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.presenter.LoginPresenter;
import cc.bikeon.app.ui.login.LoginActivity;
import cc.bikeon.app.views.LoginView;

/**
 * Responsible to create Login Presenter
 * Created by cristianoliveira on 23/08/15.
 */
public class LoginPresenterFactory {
    /**
     * Create presenter for a given View.
     * @param loginview
     * @return new LoginPresenter instance
     */
    public static LoginPresenter createFor(LoginView loginview) {
        return new LoginPresenter(loginview, new LoginRequester());
    }
}
