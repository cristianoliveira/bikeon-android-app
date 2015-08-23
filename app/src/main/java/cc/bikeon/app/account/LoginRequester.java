package cc.bikeon.app.account;

/**
 * Responsible to request login
 *
 * Created by cristianoliveira on 04/05/15.
 */
public class LoginRequester {

    /**
     * Request login with a given strategy and callback.
     * @param strategy Strategy to login
     * @param callback to request
     */
    public void requestLogin(LoginStrategy strategy, LoginCallback callback) {
        strategy.doLogin(callback);
    }
}
