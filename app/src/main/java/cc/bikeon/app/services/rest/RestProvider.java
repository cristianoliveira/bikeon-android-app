package cc.bikeon.app.services.rest;

/**
 *
 *  Provider Type for RestClient
 *
 * Created by cristianoliveira on 12/05/15.
 */
public interface RestProvider {
    /**
     *
     *  Base url of REST API provider
     *
     * @return String URL of provider
     */
    public String getBaseUrl();
}
