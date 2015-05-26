package cc.bikeon.app.services;

import cc.bikeon.app.services.rest.IRestProvider;

/**
 * Created by cristianoliveira on 12/05/15.
 */
public class StubProvider implements IRestProvider {

    final String URL = "http://google.com";

    @Override
    public String getBaseUrl() {
        return URL;
    }
}
