package cc.bikeon.app.account;

import android.util.Log;

import com.facebook.Session;
import com.facebook.SessionState;

import cc.bikeon.app.BikeOnApplication;

/**
 * Created by cristianoliveira on 05/05/15.
 */
public class FacebookSessionCallback implements Session.StatusCallback{

		ILoginCallback callback;

		public FacebookSessionCallback(ILoginCallback callback)
		{
			this.callback = callback;
		}

		@Override
		public void call(Session session, SessionState sessionState, Exception e) {
			if(session.isOpened())
			{
				BikeOnApplication.setFacebookSession(session);
				callback.onLoginSuccess();
			}
			else
			{
				if(e!=null)
				{
					callback.onLoginError(e.getMessage());
				}
				//else is oppening
			}
		}
}
