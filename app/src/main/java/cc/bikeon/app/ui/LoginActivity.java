package cc.bikeon.app.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.AppEventsLogger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.account.FacebookLoginStrategy;
import cc.bikeon.app.account.ILoginCallback;
import cc.bikeon.app.account.LoginRequester;


public class LoginActivity extends Activity
				implements View.OnClickListener, ILoginCallback {

  private static final String TAG = "MainActivity";

	private static final int LAST_VALUE_TOP_LOGO = 30;

	@InjectView(R.id.logo)
	ImageView logo;
	@InjectView(R.id.btnFacebookLogin)
	ImageButton btnFacebookLogin;

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

		btnFacebookLogin.setOnClickListener(this);

		if(!BikeOnApplication.hasSessionOpen())
		  new InitialLoadingTask().execute();
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
	public void onSuccess() {


	}

	@Override
	public void onError(String messageError) {

		new AlertDialog.Builder(this).setMessage(messageError).create().show();

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
