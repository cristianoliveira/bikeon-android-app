package cc.bikeon.app.helpers;

import android.app.Application;

/**
 * Created by cristian.rosa on 1/19/2015.
 *
 *  Methods related to the Application in general
 */
public class AppHelper {
    private static Application instance;

    public static void init(Application application)
    {
        instance = application;
    }

    public static Application getApplication()
    {
        return instance;
    }

    public static String resString(int resId)
    {
       return instance.getString(resId);
    }

}
