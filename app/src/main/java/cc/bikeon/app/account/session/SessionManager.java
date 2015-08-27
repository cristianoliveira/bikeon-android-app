package cc.bikeon.app.account.session;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

/**
 * Responsible to handle Sessions
 *
 * Created by cristianoliveira on 24/08/15.
 */
public class SessionManager {

    public final static String SHARED_SESSION_PREFERENCE = "SharedSessionPref";
    public final static String SESSION_PROVIDER = "SessionProvider";

    private SharedPreferences preferences;

    public SessionManager(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    /**
     * Return Session from Last SessionProvider saved.
     * @return Current active Session
     */
    public SessionAccount getCurrentSession() {

        String providerName = preferences.getString(SESSION_PROVIDER, null);
        SessionProvider sessionProvider = SessionProvider.valueOf(providerName);

        if (sessionProvider == SessionProvider.FACEBOOK) {
            return new FacebookSession();
        } else {
            return new BikeOnSession();
        }
    }

    /**
     * Save current Session Provider
     * @param sessionAccount
     * @return Has been data saved?
     */
    public boolean saveCurrentProvider(SessionAccount sessionAccount) {
        SessionProvider provider = sessionAccount.getProvider();

        SharedPreferences.Editor editor =  preferences.edit();
        editor.putString(SESSION_PROVIDER, provider.toString());

        return editor.commit();
    }
}
