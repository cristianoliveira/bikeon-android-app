package cc.bikeon.app.account.requesters;

import android.content.Intent;

import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.account.session.BikeOnSession;

/**
 * Created by cristianoliveira on 23/05/15.
 * <p/>
 * CLASS to use during tests.
 */
public class BikeOnLoginRequester implements LoginRequester {
    @Override
    public void doLogin(LoginCallback callback) {
        BikeOnSession session = new BikeOnSession();
        session.open();
        callback.onLoginSuccess(session);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {}

}
