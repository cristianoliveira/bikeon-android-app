package cc.bikeon.app.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.R;
import cc.bikeon.app.account.LoginStrategy;
import cc.bikeon.app.account.LoginStrategyChooser;
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

    @VisibleForTesting
    LoginPresenter presenter;

    private LoginStrategyChooser loginStrategyChooser;

    private CallbackManager mCallbackManager;
    private LoginButton mFbLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);

        presenter = LoginPresenterFactory.createFor(this, getApplicationContext());

        loginStrategyChooser = presenter.getStrategyChooser();

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

    @Override
    public void onClick(View view) {

        LoginStrategy loginStrategy;
        switch (view.getId()) {
            case R.id.btnFacebookLogin:
                loginStrategy = LoginStrategy.FACEBOOK;
                break;
            default:
                loginStrategy = LoginStrategy.BIKEON;
                break;
        }

        presenter.requestLogin(new LoginStrategyChooser(), loginStrategy, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onResult(requestCode, resultCode, data);
    }
}
