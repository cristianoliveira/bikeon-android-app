package cc.bikeon.app.account;

import android.app.Activity;

/**
 * Created by cristianoliveira on 04/05/15.
 */
public interface ILoginStrategy {

	public void doLogin(ILoginCallback callback);

}
