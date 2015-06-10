package cc.bikeon.app.account;

/**
 * Created by cristianoliveira on 04/05/15.
 */
public class LoginRequester {

	private LoginStrategy strategy;

	public void setStrategy(LoginStrategy strategy)
	{
		this.strategy = strategy;
	}

	public void requestLogin(LoginCallback callback)
	{
		strategy.doLogin(callback);
	}
}
