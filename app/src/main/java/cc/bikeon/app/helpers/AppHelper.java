package cc.bikeon.app.helpers;

import android.app.Application;

/**
 * Created by cristian.rosa on 1/19/2015.
 *
 *  Methods related to the Application in general
 */
public class AppHelper extends Application {
    private static AppHelper instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance()
    {
        return instance;
    }

    public static String resString(int resId)
    {
       return instance.getString(resId);
    }

}
