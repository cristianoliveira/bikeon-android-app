package cc.bikeon.app.api.wheather;

/**
 * Created by cristianoliveira on 09/05/15.
 */
public class OpenWeatherProvider implements IWeatherProvider {

	private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

	@Override
	public String getUrl() {
		return BASE_URL;
	}

}
