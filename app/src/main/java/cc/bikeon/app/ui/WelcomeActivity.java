package cc.bikeon.app.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.services.local.location.LocationTracker;

public class WelcomeActivity extends AppCompatActivity implements LocationListener, Dialog.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setUpLocationUpdateManager();
    }

    private void setUpLocationUpdateManager()
    {

        BikeOnApplication application = (BikeOnApplication) this.getApplication();

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
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        Intent gpsSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsSettings);

    }
}
