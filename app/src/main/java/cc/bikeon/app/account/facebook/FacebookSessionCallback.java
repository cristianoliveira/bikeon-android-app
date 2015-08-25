package cc.bikeon.app.account.facebook;

import com.facebook.Session;
import com.facebook.SessionState;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.account.LoginCallback;

/**
 * Created by cristianoliveira on 05/05/15.
 */
public class FacebookSessionCallback implements Session.StatusCallback {

    LoginCallback callback;

    public void delegateTo(LoginCallback callback) {
        this.callback = callback;
    }

    @Override
    public void call(Session session, SessionState sessionState, Exception e) {
        if (session.isOpened()) {
            BikeOnApplication.setFacebookSession(session);
            callback.onLoginSuccess(session);
        } else {
            if (e != null) {
                callback.onLoginError(e.getMessage());
            }
        }
    }
}
