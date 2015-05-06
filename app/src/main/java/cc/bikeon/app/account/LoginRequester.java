package cc.bikeon.app.account;

/**
 * Created by cristianoliveira on 04/05/15.
 */
public class LoginRequester {

	private ILoginStrategy strategy;

	public void setStrategy(ILoginStrategy strategy)
	{
		this.strategy = strategy;
	}

	public void requestLogin(ILoginCallback callback)
	{
		strategy.doLogin(callback);
	}
}
