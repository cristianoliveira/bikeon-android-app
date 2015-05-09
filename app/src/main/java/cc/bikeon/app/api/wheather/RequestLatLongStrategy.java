package cc.bikeon.app.api.wheather;

import java.io.IOException;
import java.net.URL;

/**
 * Created by cristianoliveira on 09/05/15.
 */
public class RequestLatLongStrategy implements IRequestStrategy {

	private IWeatherProvider provider;
	private float latitude;
	private float longitude;

	public RequestLatLongStrategy(IWeatherProvider provider, float latitude, float longitude)
	{
		this.provider = provider;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public java.net.URL getUrl() throws IOException {

		URL url = new URL(provider.getUrl()+"?"+String.format("lat=%s&lon=%s", latitude, longitude));
		return url;

	}

  public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

}
