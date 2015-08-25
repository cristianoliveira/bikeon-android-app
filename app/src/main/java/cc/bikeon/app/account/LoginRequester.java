package cc.bikeon.app.account;

/**
 * Represent strategy of authentication.
 * Created by cristianoliveira on 04/05/15.
 */
public interface LoginRequester {

    void doLogin(LoginCallback callback);

}
