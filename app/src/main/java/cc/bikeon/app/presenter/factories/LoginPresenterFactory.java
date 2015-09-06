package cc.bikeon.app.presenter.factories;

import android.content.Context;

import cc.bikeon.app.account.session.SessionManager;
import cc.bikeon.app.presenter.LoginPresenter;
import cc.bikeon.app.views.LoginView;

/**
 * Responsible to create instances for LoginPresenter
 * Created by cristianoliveira on 26/08/15.
 */
public class LoginPresenterFactory {
    public static LoginPresenter createFor(LoginView view, Context context) {
        SessionManager sessionManager = SessionManager.Factory.create(context);
        return new LoginPresenter(view, sessionManager);
    }
}
