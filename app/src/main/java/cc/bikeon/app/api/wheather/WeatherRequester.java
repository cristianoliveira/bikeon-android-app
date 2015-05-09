package cc.bikeon.app.api.wheather;

import com.google.gson.Gson;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URLConnection;

import cc.bikeon.app.util.ParserUtil;

/**
 * Created by cristianoliveira on 08/05/15.
 */
public class WeatherRequester {

	public Weather request(IRequestStrategy requestStrategy) throws IOException, URISyntaxException {
		URLConnection con = requestStrategy.getUrl().openConnection();

		InputStream inputStream = con.getInputStream();

		String jsonContent = ParserUtil.InputStreamToString(inputStream);

    Weather weather = new Gson().fromJson(jsonContent, Weather.class);

		return weather;
	}

}
