package cc.bikeon.app.account.session;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import cc.bikeon.app.BikeOnApplication;

/**
 * Facebook implementation of SessionAccount for Facebook SDK 4.0
 * Created by cristianoliveira on 24/08/15.
 */
public class FacebookSession implements SessionAccount {

    private AccessToken session;

    public FacebookSession() {
        FacebookSdk.sdkInitialize(BikeOnApplication.getInstance());
        this.session = AccessToken.getCurrentAccessToken();
    }

    public FacebookSession(AccessToken session) {
        this.session = session;
    }

    @Override
    public SessionProvider getProvider() {
        return SessionProvider.FACEBOOK;
    }

    @Override
    public void close() {
        LoginManager login = LoginManager.getInstance();
        login.logOut();
    }

    @Override
    public boolean isActive() {
        return session != null? true : false;
    }

    @Override
    public String getToken() {
        return session.getToken();
    }

}
