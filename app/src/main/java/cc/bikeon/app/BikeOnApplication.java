package cc.bikeon.app;

import android.app.Application;

/**
 * Created by cristian.rosa on 1/19/2015.
 */
public class BikeOnApplication extends Application {

    private static BikeOnApplication instance;

    public static BikeOnApplication getInstance() {
        return instance;
    }

    public static String getStringResource(int resId) {
        return instance.getString(resId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
