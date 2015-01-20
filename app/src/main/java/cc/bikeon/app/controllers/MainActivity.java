package cc.bikeon.app.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;

import cc.bikeon.app.R;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    Session.StatusCallback sessionCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState sessionState, Exception e) {
             onSessionStateChange(session, sessionState, e);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }
}
