package cc.bikeon.app.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.presenter.WelcomePresenter;
import cc.bikeon.app.services.local.location.LocationTracker;
import cc.bikeon.app.ui.login.LoginActivity;

public class WelcomeActivity extends Activity
        implements WelcomeView,DialogInterface.OnClickListener {

    private WelcomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        presenter = new WelcomePresenter(this);
        presenter.verifyLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    @Override
    public void gotoLogin() {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
        this.finish();
    }

    @Override
    public void showError(int stringResId) {
        new AlertDialog
                .Builder(this)
                .setMessage(this.getString(stringResId))
                .setNeutralButton("OK", this)
                .show();
    }
}
