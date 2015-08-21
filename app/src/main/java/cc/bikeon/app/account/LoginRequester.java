package cc.bikeon.app.account;

/**
 * Responsible to manage login request with a given
 * login strategy.
 * <p/>
 * Created by cristianoliveira on 04/05/15.
 */
public class LoginRequester {

    private LoginStrategy strategy;
    private LoginCallback callback;

    public void setStrategy(LoginStrategy strategy) {
        this.strategy = strategy;
    }

    public void setCallback(LoginCallback callback) {
        this.callback = callback;
    }

    public void requestLogin() {
        strategy.doLogin(callback);
    }
}
