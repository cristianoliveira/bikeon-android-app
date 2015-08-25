package cc.bikeon.app.account.facebook;

import android.app.Activity;
import android.content.Intent;

import com.facebook.Session;
import com.facebook.UiLifecycleHelper;

import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.LoginRequester;

/**
 * Facebook Implementation of Login Strategy.
 * Created by cristianoliveira on 04/05/15.
 */
public class FacebookLoginRequester extends Activity implements LoginRequester {

    private UiLifecycleHelper uiHelper;
    private FacebookSessionCallback facebookSessionCallback;

    public FacebookLoginRequester(FacebookSessionCallback facebookSessionCallback) {
        this.facebookSessionCallback = facebookSessionCallback;
    }

    @Override
    public void doLogin(LoginCallback callback) {
        facebookSessionCallback.delegateTo(callback);
        uiHelper = new UiLifecycleHelper(this, facebookSessionCallback);
        Session.openActiveSession(this, true, facebookSessionCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data);
    }


}
