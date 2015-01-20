package cc.bikeon.app;

import android.app.Application;

import cc.bikeon.app.helpers.AppHelper;

/**
 * Created by cristian.rosa on 1/19/2015.
 */
public class BikeOnApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppHelper.init(this);
    }
}
