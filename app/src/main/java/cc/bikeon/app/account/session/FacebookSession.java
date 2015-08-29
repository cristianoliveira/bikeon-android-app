package cc.bikeon.app.account.session;

import com.facebook.Session;

/**
 * Facebook implementation of SessionAccount
 * Created by cristianoliveira on 24/08/15.
 */
public class FacebookSession implements SessionAccount {

    private Session session;

    public FacebookSession() {
        this.session = Session.getActiveSession();
    }

    public FacebookSession(Session session) {
        this.session = session;
    }

    @Override
    public SessionProvider getProvider() {
        return SessionProvider.FACEBOOK;
    }

    @Override
    public void close() {
        session.close();
    }

    @Override
    public boolean isActive() {
        return session != null? session.isOpened() : false;
    }

    @Override
    public String getToken() {
        return session.getAccessToken();
    }

}
