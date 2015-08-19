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
import cc.bikeon.app.R;
import cc.bikeon.app.presenter.MainPresenter;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks {


    private static final String TAG = "MainActivity";

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    private MainPresenter presenter;

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

        mNavigationDrawerFragment.closeDrawer();

        ButterKnife.inject(this);
        presenter = new MainPresenter();
        replaceFragmentWith(presenter.getLocationFragment());
    }

    public MainPresenter getPresenter() {
        return presenter;
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

    public void showLocationFragment() {
        replaceFragmentWith(presenter.getLocationFragment());
    }

    public void showMapNavigationFragment(String destination) {
        replaceFragmentWith(presenter.getMapFragment(destination));
    }

}
