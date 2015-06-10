package cc.bikeon.app.services.rest.directions.google;

import cc.bikeon.app.services.rest.RestProvider;
import cc.bikeon.app.services.rest.directions.DirectionConstants;

/**
 * Created by cristianoliveira on 31/05/15.
 */
public class GoogleDirectionProvider implements RestProvider {
    @Override
    public String getBaseUrl() {
        return DirectionConstants.GOOGLE_URL_PROVIDER;
    }
}
