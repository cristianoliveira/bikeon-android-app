package cc.bikeon.app.account;

/**
 * Created by cristianoliveira on 04/05/15.
 */
public interface LoginCallback<T> {
    public void onLoginSuccess(T SessionType);

    public void onLoginError(String messageError);
}
