package cc.bikeon.app.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.LoginButton;

import java.text.Annotation;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.R;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    public static final int LAST_VALUE_TOP_LOGO = 30;

    @InjectView(R.id.logo)
    ImageView logo;
    @InjectView(R.id.facebookLoginButton)
    LoginButton facebookLoginBtn;

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
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LogoAnimation logoAnimation = new LogoAnimation();
        logoAnimation.start();

        AppEventsLogger.activateApp(this);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }

    class LogoAnimation extends Animation{

        LogoAnimation()
        {
            this.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    facebookLoginBtn.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}

                @Override
                public void onAnimationStart(Animation animation) {}
            });
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t)
        {
            logo.setTop(LAST_VALUE_TOP_LOGO);
        }
    }
}
