package cc.bikeon.app.account.session;

import java.util.List;

/**
 * Manage session for a given providers
 *
 * Created by cristianoliveira on 24/08/15.
 */
public class SessionFinder {

    /**
     * For a given providers return the only with a session active.
     * @param providers
     * @return Current Provider with active Session
     */
    public SessionProvider getCurrentProvider(List<SessionProvider> providers) {
        for (SessionProvider provider : providers) {
            if (provider.hasSessionActive()) {
                return provider;
            }
        }
        return null;
    }
}
