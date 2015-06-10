package cc.bikeon.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import butterknife.ButterKnife;
import cc.bikeon.app.R;
import cc.bikeon.app.services.rest.directions.GeocodeResponse;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionResponse;
import cc.bikeon.app.services.rest.directions.google.GoogleDirectionService;
import cc.bikeon.app.services.rest.directions.google.GoogleMapRouter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cristianoliveira on 06/06/15.
 */
public class MapNavigationFragment extends MapFragment implements OnMapReadyCallback,
        Callback<GoogleDirectionResponse> {

    private final String TAG = "MapNavigationFragment";

    GoogleDirectionService googleDirectionService;
    GoogleDirectionResponse googleDirectionResponse;
    GoogleMap googleMap;
    List<LatLng> directions;
    MainActivity mainActivity;


    public MapNavigationFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.inject(this, view);

        this.getMapAsync(this);

        mainActivity = (MainActivity) getActivity();

        return view;
    }

    public void setDirections(GeocodeResponse directions)
    {
        List<LatLng> steps = directions.getData();
        if (directions != null && steps.size() > 0) {

            GoogleMapRouter.doRoute(googleMap, steps);

            this.directions = directions.getData();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public GoogleMap getGoogleMap() {
        return this.googleMap;
    }

    @Override
    public void success(GoogleDirectionResponse googleDirectionResponse, Response response) {
        setDirections(googleDirectionResponse);
    }

    @Override
    public void failure(RetrofitError error) {

    }
}
