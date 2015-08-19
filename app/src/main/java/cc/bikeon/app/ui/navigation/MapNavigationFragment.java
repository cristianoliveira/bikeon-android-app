package cc.bikeon.app.ui.navigation;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import butterknife.ButterKnife;
import cc.bikeon.app.R;
import cc.bikeon.app.presenter.MapNavigationPresenter;
import cc.bikeon.app.presenter.factories.MapNavigationPresenterFactory;
import cc.bikeon.app.ui.main.MainActivity;

/**
 * Created by cristianoliveira on 06/06/15.
 */
public class MapNavigationFragment extends MapFragment implements OnMapReadyCallback, MapNavigationView {

    private final String TAG = "MapNavigationFragment";
    private final int FIRST = 0;

    private GoogleMap googleMap;
    private MainActivity mainActivity;

    private MapNavigationPresenter presenter;

    public MapNavigationFragment() {
        presenter = MapNavigationPresenterFactory.createFor(this);
    }

    public void setPresenter(MapNavigationPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container, savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_map_navigation, container, false);
        ButterKnife.inject(this, view);

        mainActivity = (MainActivity) getActivity();

        return view;
    }

    @Override
    public void setMapRoute(List<LatLng> points) {
        if (googleMap != null && points != null && points.size() > 0) {

            googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(points.get(FIRST),
                            MapNavigationConstants.NAVIGATION_ZOOM)
            );

            googleMap.animateCamera(
                    CameraUpdateFactory.zoomTo(
                            MapNavigationConstants.NAVIGATION_ZOOM),
                            MapNavigationConstants.ZOOM_DURATION, null);

            PolylineOptions polynesOpt = new PolylineOptions();
            polynesOpt.addAll(points);

            googleMap.addPolyline(polynesOpt);
        }
    }



    @Override
    public void showMessageError(String message) {
        new AlertDialog.Builder(mainActivity).setMessage(message).show();
    }

    @Override
    public void setDestination(String destination) {
        presenter.setDirections("Canoas", destination);
    }

    @Override
    public void showInvalidDestinationError() {
        new AlertDialog.Builder(getActivity()).setMessage(R.string.message_error_encode).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        presenter.requestDirections();
    }
}
