package cc.bikeon.app.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.account.FacebookLoginStrategy;
import cc.bikeon.app.account.ILoginCallback;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.services.RestClient;
import cc.bikeon.app.services.weather.OpenWeatherProvider;
import cc.bikeon.app.services.weather.WeatherService;


public class LoginActivity extends Activity
                implements View.OnClickListener, ILoginCallback{

    private static final String TAG = "MainActivity";

    private static final int LAST_VALUE_TOP_LOGO = 30;

    @InjectView(R.id.logo)
    ImageView logo;
    @InjectView(R.id.btnFacebookLogin)
    ImageButton btnFacebookLogin;
    private UiLifecycleHelper uiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        RestClient service = new RestClient(new OpenWeatherProvider());
        WeatherService weatherService = (WeatherService) service.getService(WeatherService.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);

        btnFacebookLogin.setOnClickListener(this);
    }

    private void changeLayout() {
            RelativeLayout.LayoutParams  lp =
                            (RelativeLayout.LayoutParams) logo.getLayoutParams();

            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);

            logo.setLayoutParams(lp);
            btnFacebookLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {

        LoginRequester loginRequester = new LoginRequester();

        switch (v.getId()) {
            case R.id.btnFacebookLogin:
                loginRequester.setStrategy(new FacebookLoginStrategy(this));
        }

        loginRequester.requestLogin(this);
    }

    @Override
    public void onLoginSuccess() {

        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    this.finish();
    }

    @Override
    public void onLoginError(String messageError) {
        new AlertDialog
                        .Builder(this)
                        .setMessage(messageError)
                        .create()
                        .show();
    }
}