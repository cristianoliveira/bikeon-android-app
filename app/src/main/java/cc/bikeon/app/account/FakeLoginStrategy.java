package cc.bikeon.app.account;

/**
 * Created by cristianoliveira on 23/05/15.
 *
 *  CLASS to use during tests.
 *
 */
public class FakeLoginStrategy implements ILoginStrategy {
    @Override
    public void doLogin(ILoginCallback callback) {
        callback.onLoginSuccess();
    }
}
