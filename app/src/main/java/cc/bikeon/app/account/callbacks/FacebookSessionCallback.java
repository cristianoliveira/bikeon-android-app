package cc.bikeon.app.account.callbacks;

import com.facebook.Session;
import com.facebook.SessionState;

import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.session.FacebookSession;
import cc.bikeon.app.account.session.SessionAccount;

/**
 * Facebook Callback responsible to handle Facebook Login Request.
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
            SessionAccount sessionAccount = new FacebookSession();
            callback.onLoginSuccess(sessionAccount);
        } else {
            if (e != null) {
                callback.onLoginError(e.getMessage());
            }
        }
    }
}
