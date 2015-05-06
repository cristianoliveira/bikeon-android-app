package cc.bikeon.app.account;

import android.app.Activity;

import com.facebook.Session;
import com.facebook.SessionState;

import cc.bikeon.app.BikeOnApplication;

/**
 * Created by cristianoliveira on 04/05/15.
 */
public class FacebookLoginStrategy implements ILoginStrategy {

	Activity activity;

	public FacebookLoginStrategy(Activity activity)
	{
    this.activity = activity;
	}

	@Override
	public void doLogin(ILoginCallback callback) {
		Session.openActiveSession(
						activity,
						true,
						getCallBack(callback));
	}

  public FacebookSessionCallback getCallBack(ILoginCallback callback)
	{
		return new FacebookSessionCallback(callback);
	}

}
