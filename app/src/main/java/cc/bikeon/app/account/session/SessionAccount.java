package cc.bikeon.app.account.session;

/**
 * Represent a API that provide a session handler.
 * Created by cristianoliveira on 24/08/15.
 */
public interface SessionAccount {

    /**
     * Return Provider for this session account.
     * @return Provider
     */
    SessionProvider getProvider();

    /**
     * Close current session if it exists
     */
    void closeSession();

    /**
     * Verify if has an Active Session.
     * @return
     */
    boolean hasSessionActive();


    /**
     * Retrieve token
     */
    String getToken();
}
