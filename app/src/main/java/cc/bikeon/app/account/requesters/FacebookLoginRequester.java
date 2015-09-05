package cc.bikeon.app.account.requesters;

import android.app.Activity;
import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.util.Arrays;

import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.account.callbacks.FacebookSessionCallback;

/**
 * Facebook Implementation of Login Requester.
 * Created by cristianoliveira on 04/05/15.
 */
public class FacebookLoginRequester implements LoginRequester {

    private Activity activity;
    private FacebookSessionCallback facebookSessionCallback;
    private CallbackManager callbackManager;

    public FacebookLoginRequester(CallbackManager callbackManager,
                                  FacebookSessionCallback facebookSessionCallback,
                                  Activity activity) {
        this.callbackManager = callbackManager;
        this.facebookSessionCallback = facebookSessionCallback;
        this.activity = activity;
    }

    @Override
    public void doLogin(LoginCallback applicationLoginCallback) {
        facebookSessionCallback.delegate(applicationLoginCallback);

        FacebookSdk.sdkInitialize(activity.getApplicationContext());

        LoginManager loginManager = LoginManager.getInstance();

        loginManager.registerCallback(callbackManager, facebookSessionCallback);

        loginManager.logInWithReadPermissions(activity, Arrays.asList("basic_info"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public static class Factory {
        public static FacebookLoginRequester create(Activity activity) {
            return new FacebookLoginRequester(
                    CallbackManager.Factory.create(),
                    new FacebookSessionCallback(),
                    activity);
        }
    }

}
