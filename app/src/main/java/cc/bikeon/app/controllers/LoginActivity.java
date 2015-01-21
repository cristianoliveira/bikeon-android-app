package cc.bikeon.app.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.LoginButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;


public class LoginActivity extends Activity {
    private static final String TAG = "MainActivity";
    public static final int LAST_VALUE_TOP_LOGO = 30;

    @InjectView(R.id.logo)
    ImageView logo;
    @InjectView(R.id.facebookLoginButton)
    LoginButton facebookLoginBtn;

    Session state;

    Session.StatusCallback sessionCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState sessionState, Exception e) {
             onSessionStateChange(session, sessionState, e);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);

        if(!BikeOnApplication.hasSessionOpen())
           new InitialLoadingTask().execute();
    }

    private void changeLayout() {
        RelativeLayout.LayoutParams  lp =
                (RelativeLayout.LayoutParams) logo.getLayoutParams();

        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        logo.setLayoutParams(lp);
        facebookLoginBtn.setVisibility(View.VISIBLE);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {

            BikeOnApplication.setFacebookSession(session);
            startActivity(new Intent(this, MainActivity.class));

        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
            new AlertDialog.Builder(this)
                           .setTitle(R.string.message_title_alert)
                           .setMessage(R.string.message_error_login).show();
        }
    }

    class InitialLoadingTask extends AsyncTask<Object, Integer, Object>
    {
        @Override
        protected Object doInBackground(Object... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            changeLayout();

        }
    }

}
