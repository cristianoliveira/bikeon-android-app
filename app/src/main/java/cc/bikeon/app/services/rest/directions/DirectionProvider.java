package cc.bikeon.app.services.rest.directions;

import cc.bikeon.app.services.rest.RestProvider;

/**
 * Created by cristianoliveira on 31/05/15.
 */
public class DirectionProvider implements RestProvider {
    @Override
    public String getBaseUrl() {
        return DirectionConstants.GOOGLE_URL_PROVIDER;
    }
}
