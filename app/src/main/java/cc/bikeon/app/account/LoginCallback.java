package cc.bikeon.app.account;

/**
 * Created by cristianoliveira on 04/05/15.
 */
public interface LoginCallback {
	public void onLoginSuccess();
	public void onLoginError(String messageError);
}
