package cc.bikeon.app;

import android.app.Application;

import com.facebook.Session;

import cc.bikeon.app.helpers.AppHelper;

/**
 * Created by cristian.rosa on 1/19/2015.
 */
public class BikeOnApplication extends Application {

    private static Session facebookSession;

    @Override
    public void onCreate() {
        super.onCreate();
        AppHelper.init(this);
    }

    public static void setFacebookSession(Session session)
    {
        facebookSession = session;
    }

    public static Session getFacebookSession()
    {
        return facebookSession;
    }

    public static boolean hasSessionOpen()
    {
        return facebookSession!=null? facebookSession.isOpened() : false;
    }

    public static boolean isSessionClosed()
    {
        return facebookSession!=null? facebookSession.isClosed() : true;
    }
}
