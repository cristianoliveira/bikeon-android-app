package cc.bikeon.app.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.services.local.location.LocationTracker;

public class WelcomeActivity extends FragmentActivity implements DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        BikeOnApplication application =
                (BikeOnApplication) getApplication();

        LocationManager locationManager = (LocationManager)
                application.getSystemService(Context.LOCATION_SERVICE);

        LocationTracker locationTracker = new LocationTracker(locationManager);
        boolean isServiceEnabled = locationTracker.isLocationServiceEnabled();

        if(isServiceEnabled){

            application.setLocationTracker(locationTracker);

            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
            this.finish();

        }else {

            new AlertDialog
                    .Builder(this)
                    .setMessage(this.getString(R.string.message_error_location))
                    .setNeutralButton("OK", this)
                    .show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
