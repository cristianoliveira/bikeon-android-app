package cc.bikeon.app.account;

/**
 * Created by cristianoliveira on 04/05/15.
 */
public interface ILoginCallback {
	public void onLoginSuccess();
	public void onLoginError(String messageError);
}