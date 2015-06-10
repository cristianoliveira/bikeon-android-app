package cc.bikeon.app.services.rest.directions;

import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import cc.bikeon.app.services.rest.directions.google.GoogleMapRouter;
import cc.bikeon.app.ui.WelcomeActivity;

/**
 * Created by cristianoliveira on 07/06/15.
 */
public class GoogleMapRouterTest extends ActivityInstrumentationTestCase2<WelcomeActivity> {

    WelcomeActivity welcomeActivity;

    public GoogleMapRouterTest() {
        super(WelcomeActivity.class);
    }

    @MediumTest
    public void testWhenDoRouteItShouldPrintLine() {
        // given
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        MapsInitializer.initialize(getActivity());
        MapFragment mapFragment = new MapFragment();
        fragmentManager.beginTransaction().add(mapFragment,"a").commit();

        GoogleMap googleMap = mapFragment.getMap();
        LatLng startPosition = new LatLng(0,0);
        List<LatLng> directions = new ArrayList<LatLng>();
        directions.add(startPosition);
        directions.add(new LatLng(1,1));
        directions.add(new LatLng(2,2));
        directions.add(new LatLng(3, 4));

        // when
        GoogleMapRouter.doRoute(googleMap, directions);

        // then not raise error
    }
}
