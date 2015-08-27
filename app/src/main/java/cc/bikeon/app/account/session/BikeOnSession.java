package cc.bikeon.app.account.session;

import android.app.Application;
import android.content.SharedPreferences;

import cc.bikeon.app.BikeOnApplication;

/**
 * Session Provided by bikeon.cc
 *
 * This is a fake session to use during development.
 *
 * TODO Remove before release
 *
 * Created by cristianoliveira on 25/08/15.
 */
public class BikeOnSession implements SessionAccount {

    private final String SESSION = "BikeonSession";

    public void open() {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(SESSION, true);
        editor.commit();
    }

    @Override
    public SessionProvider getProvider() {
        return SessionProvider.BIKEON;
    }

    @Override
    public void closeSession() {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(SESSION, false);
        editor.commit();
    }

    @Override
    public boolean hasSessionActive() {
        Application application = BikeOnApplication.getInstance();
        SharedPreferences preferences =
                application.getSharedPreferences(SESSION, application.MODE_PRIVATE);
        return preferences.getBoolean(SESSION, false);
    }

    @Override
    public String getToken() {
        return "fake token";
    }

    private SharedPreferences.Editor getEditor() {
        Application application = BikeOnApplication.getInstance();
        SharedPreferences preferences =
                application.getSharedPreferences(SESSION, application.MODE_PRIVATE);
        return preferences.edit();
    }
}
