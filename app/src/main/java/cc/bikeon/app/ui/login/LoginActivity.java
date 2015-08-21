package cc.bikeon.app.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.facebook.UiLifecycleHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.R;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.presenter.LoginPresenter;
import cc.bikeon.app.ui.main.MainActivity;

public class LoginActivity extends Activity
                implements LoginView {

    private final String TAG = "MainActivity";

    @InjectView(R.id.logo)
    ImageView logo;
    @InjectView(R.id.btnFacebookLogin)
    ImageButton btnFacebookLogin;
    private UiLifecycleHelper uiHelper;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        presenter = new LoginPresenter(this, new LoginRequester());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEvents();
    }

    private void setEvents() {
        btnFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO remove after tests
                presenter.requestLogin(10);
            }
        });
    }

    @Override
    public void showLoginError(String messageError) {
        new AlertDialog.Builder(this)
                       .setMessage(messageError)
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
}
