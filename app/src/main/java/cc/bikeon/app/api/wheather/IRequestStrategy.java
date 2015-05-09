package cc.bikeon.app.api.wheather;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by cristianoliveira on 09/05/15.
 */
public interface IRequestStrategy {

	public java.net.URL getUrl() throws IOException, URISyntaxException;
}
