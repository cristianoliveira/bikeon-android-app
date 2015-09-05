package cc.bikeon.app.ui.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cc.bikeon.app.R;
import cc.bikeon.app.presenter.MainPresenter;
import cc.bikeon.app.ui.main.menu.NavigationDrawerCallbacks;
import cc.bikeon.app.ui.main.menu.NavigationDrawerFragment;
import cc.bikeon.app.ui.main.menu.NavigationMenuItems;
import cc.bikeon.app.views.MainView;


public class MainActivity extends AppCompatActivity
        implements MainView, NavigationDrawerCallbacks, LocationFragment.DestinationListener {


    private static final String TAG = "MainActivity";

    @InjectView(R.id.toolbar_actionbar)
    Toolbar mToolbar;

    NavigationDrawerFragment mNavigationDrawerFragment;
    @InjectView(R.id.drawer)
    DrawerLayout drawerLayout;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);


        mNavigationDrawerFragment.setup(
                R.id.fragment_drawer,
                drawerLayout,
                mToolbar);

        mNavigationDrawerFragment.closeDrawer();


        presenter = new MainPresenter(this);
        replaceFragmentWith(presenter.getLocationFragment(this));
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onNavigationDrawerItemSelected(int menuItem) {
        switch (menuItem){
            case NavigationMenuItems.PROFILE:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case NavigationMenuItems.FAVORITE_ROUTES:
                Toast.makeText(this, "Favorite Routes", Toast.LENGTH_SHORT).show();
                break;
            case NavigationMenuItems.LOGOUT:
                presenter.doLogout(getBaseContext());

        }
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
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void replaceFragmentWith(Fragment newFragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_content, newFragment)
                .commit();
    }

    @Override
    public void onLogout() {
        finish();
    }

    @Override
    public void onDestinationSelect(String destination) {
        replaceFragmentWith(presenter.getMapFragment(destination));
    }
}
