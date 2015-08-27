package cc.bikeon.app.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.R;
import cc.bikeon.app.account.requesters.FacebookLoginRequester;
import cc.bikeon.app.account.callbacks.FacebookSessionCallback;
import cc.bikeon.app.account.requesters.BikeOnLoginRequester;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.presenter.LoginPresenter;
import cc.bikeon.app.presenter.factories.LoginPresenterFactory;
import cc.bikeon.app.ui.main.MainActivity;
import cc.bikeon.app.views.LoginView;

public class LoginActivity extends Activity
                implements LoginView, View.OnClickListener {

    @InjectView(R.id.logo)
    ImageView logo;
    @InjectView(R.id.btnFacebookLogin)
    ImageButton btnFacebookLogin;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        presenter = LoginPresenterFactory.createFor(this, getApplicationContext());

        btnFacebookLogin.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.verifySession();
    }

    /**
     * Setter for Login presenter.
     * @param presenter
     */
    public void setLoginPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(int messageErrorResId) {
        new AlertDialog.Builder(this)
                       .setMessage(messageErrorResId)
                       .create()
                       .show();
    }

    @Override
    public void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    public void onClick(View view) {
        LoginRequester loginRequester = null;

        switch (view.getId()) {
            case R.id.btnFacebookLogin:
                loginRequester =
                        new FacebookLoginRequester(new FacebookSessionCallback());
                break;
        }

        //TODO remove after tests
        loginRequester = new BikeOnLoginRequester();

        presenter.requestLogin(loginRequester);
    }
}
