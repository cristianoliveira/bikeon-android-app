package cc.bikeon.app.account;

/**
 * Created by cristianoliveira on 23/05/15.
 */
public class FakeLoginStrategy implements ILoginStrategy {
    @Override
    public void doLogin(ILoginCallback callback) {
        callback.onLoginSuccess();
    }
}
