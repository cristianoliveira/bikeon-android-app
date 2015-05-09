package cc.bikeon.app.account;

import android.app.Activity;
import android.content.Intent;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

import cc.bikeon.app.BikeOnApplication;

/**
 * Created by cristianoliveira on 04/05/15.
 */
public class FacebookLoginStrategy extends Activity implements ILoginStrategy {

	private Activity activity;
	private UiLifecycleHelper uiHelper;
  private FacebookSessionCallback facebookSessionCallback;

	public FacebookLoginStrategy(Activity activity)
	{
    this.activity = activity;
	}

	@Override
	public void doLogin(ILoginCallback callback) {

	  closeActiveSession();

		FacebookSessionCallback facebookSessionCallback  = getCallBack(callback);

		uiHelper = new UiLifecycleHelper(activity, facebookSessionCallback);

		Session.openActiveSession(
						activity,
						true,
						facebookSessionCallback);

	}

	private void closeActiveSession()
	{
		if(Session.getActiveSession()!= null)
		{
			Session.getActiveSession().closeAndClearTokenInformation();
		}
	}

  public FacebookSessionCallback getCallBack(ILoginCallback callback)
	{
		if(facebookSessionCallback==null)
		{
			facebookSessionCallback = new FacebookSessionCallback(callback);
		}
		return facebookSessionCallback;
	}

	public FacebookSessionCallback getFacebookSessionCallback() {
		return facebookSessionCallback;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		getUiHelper().onActivityResult(requestCode, resultCode, data);
	}

	private UiLifecycleHelper getUiHelper()
	{
		if(uiHelper == null)
			uiHelper = new UiLifecycleHelper(this, getFacebookSessionCallback());
		return uiHelper;
	}

}
