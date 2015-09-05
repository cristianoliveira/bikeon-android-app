package cc.bikeon.app.account.callbacks;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.session.FacebookSession;

/**
 * Facebook Callback responsible to handle Facebook Login Request.
 * Created by cristianoliveira on 05/05/15.
 */
public class FacebookSessionCallback implements FacebookCallback<LoginResult> {

    private LoginCallback loginCallback;

    public void delegate(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        loginCallback.onLoginSuccess(new FacebookSession(loginResult.getAccessToken()));
    }

    @Override
    public void onCancel() {
        loginCallback.onLoginError("Cancel");
    }

    @Override
    public void onError(FacebookException e) {
        loginCallback.onLoginError("Error: "+e.getMessage());
    }
}
