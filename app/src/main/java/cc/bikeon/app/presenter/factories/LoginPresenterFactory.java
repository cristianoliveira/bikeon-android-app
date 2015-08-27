package cc.bikeon.app.presenter.factories;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.account.session.SessionManager;
import cc.bikeon.app.presenter.LoginPresenter;
import cc.bikeon.app.ui.login.LoginActivity;
import cc.bikeon.app.views.LoginView;

/**
 * Responsible to create instances for LoginPresenter
 * Created by cristianoliveira on 26/08/15.
 */
public class LoginPresenterFactory {
    public static LoginPresenter createFor(LoginView view, Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(
                        SessionManager.SHARED_SESSION_PREFERENCE,
                        context.MODE_PRIVATE
                );
        SessionManager sessionManager = new SessionManager(prefs);
        return new LoginPresenter(view, sessionManager);
    }
}
