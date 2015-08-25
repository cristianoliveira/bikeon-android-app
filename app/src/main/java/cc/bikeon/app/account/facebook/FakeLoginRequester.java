package cc.bikeon.app.account.facebook;

import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.LoginRequester;

/**
 * Created by cristianoliveira on 23/05/15.
 * <p/>
 * CLASS to use during tests.
 */
public class FakeLoginRequester implements LoginRequester {
    @Override
    public void doLogin(LoginCallback callback) {
        callback.onLoginSuccess(null);
    }
}
