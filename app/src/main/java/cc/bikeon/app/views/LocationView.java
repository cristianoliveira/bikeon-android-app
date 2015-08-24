package cc.bikeon.app.views;

import android.location.Location;

/**
 * View interface for location presenter
 * Created by cristianoliveira on 22/08/15.
 */
public interface LocationView extends View {
    void onUpdateLocation(Location location);
    void onProviderChange(String s);
    void showMessageLocationServiceDisabled();
}
