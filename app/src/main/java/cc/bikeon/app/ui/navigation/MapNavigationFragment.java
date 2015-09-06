package cc.bikeon.app.ui.navigation;

import android.app.AlertDialog;
import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import butterknife.ButterKnife;
import cc.bikeon.app.R;
import cc.bikeon.app.domain.directions.Coordinate;
import cc.bikeon.app.internal.parsers.LatLngParser;
import cc.bikeon.app.presenter.MapNavigationPresenter;
import cc.bikeon.app.presenter.factories.MapNavigationPresenterFactory;
import cc.bikeon.app.ui.main.MainActivity;
import cc.bikeon.app.views.MapNavigationView;

/**
 * Fragment UI View that contain a navigable map.
 *
 * Created by cristianoliveira on 06/06/15.
 */
public class MapNavigationFragment extends Fragment
                                   implements OnMapReadyCallback, MapNavigationView {

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
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_map_navigation, container, false);
        ButterKnife.inject(this, view);

        mainActivity = (MainActivity) getActivity();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void setMapRoute(List<Coordinate> points) {
        LatLngParser parser = new LatLngParser();

        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(parser.parse(points.get(FIRST)))
                        .zoom(MapNavigationConstants.NAVIGATION_ZOOM)
                        .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.animateCamera(
                CameraUpdateFactory.zoomTo(
                        MapNavigationConstants.NAVIGATION_ZOOM),
                MapNavigationConstants.ZOOM_DURATION, null);

        PolylineOptions polynesOpt = new PolylineOptions();
        polynesOpt.color(R.color.map_navigation_route);

        for(Coordinate coordinate:points){
            polynesOpt.add(parser.parse(coordinate));
        }
        polynesOpt.geodesic(true);

        googleMap.addPolyline(polynesOpt);

    }

    @Override
    public void showError(int message) {
        new AlertDialog.Builder(mainActivity).setMessage(message).show();
    }

    @Override
    public void setDestination(String origin, String destination) {
        presenter.setDirections(origin, destination);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        presenter.requestDirections();
    }
}
