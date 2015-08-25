package cc.bikeon.app.account.session;

import com.facebook.Session;

/**
 * Facebook implementation of SessionAccount
 * Created by cristianoliveira on 24/08/15.
 */
public class FacebookSession implements SessionAccount {

    private Session session;

    public FacebookSession(Session session) {
        this.session = session;
    }

    @Override
    public void closeSession() {
        session.close();
    }

    @Override
    public boolean hasSessionActive() {
        return session != null? session.isOpened() : false;
    }

    @Override
    public String getToken() {
        return session.getAccessToken();
    }

}
