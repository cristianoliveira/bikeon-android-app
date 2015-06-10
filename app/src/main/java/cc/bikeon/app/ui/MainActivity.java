package cc.bikeon.app.ui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import butterknife.ButterKnife;
import cc.bikeon.app.R;
import cc.bikeon.app.services.rest.RestServiceFactory;
import cc.bikeon.app.services.rest.directions.DirectionRequester;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionProvider;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionRequester;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionService;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks, FragmentInteractionListner {


    private static final String TAG = "MainActivity";

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    private LocationManager locationManager;
    private String destination;

    private Fragment currentFragment;
    private LocationFragment locationFragment;
    private MapNavigationFragment mapFragment;

    private DirectionRequester directionRequester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer,
                (DrawerLayout) findViewById(R.id.drawer),
                mToolbar);

        ButterKnife.inject(this);

//        setLocationFragment();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
    public void onFragmentInteraction(Class fragment, Uri uri) {

        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
    }

    private void setLocationFragment() {
        replaceContentFragmentWith(getLocationFragment());
    }

    public void openMap(String destination) {
        this.destination = destination;

        MapNavigationFragment mapFragment = getMapFragment();

        try {
            getDirectionRequester().request("Porto Alegre", destination, mapFragment);
            replaceContentFragmentWith(mapFragment);
        }catch (UnsupportedEncodingException unex) {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.message_error_encode)
                    .show();
        }

    }

    private DirectionRequester getDirectionRequester() {
        if(directionRequester == null) {
            directionRequester = new GoogleDirectionRequester(getString(R.string.google_maps_key));
        }
        return directionRequester;
    }

    private void replaceContentFragmentWith(Fragment newFragment) {
        currentFragment = newFragment;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_drawer, newFragment)
                .commit();
    }

    public String getDestination() {
        return this.destination;
    }

    public LocationFragment getLocationFragment() {
        if (locationFragment == null) {
            locationFragment = new LocationFragment();
        }
        return locationFragment;
    }

    public MapNavigationFragment getMapFragment() {
        if (mapFragment == null) {
            mapFragment = new MapNavigationFragment();
        }
        return mapFragment;
    }

}
