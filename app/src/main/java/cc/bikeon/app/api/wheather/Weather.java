package cc.bikeon.app.api.wheather;

/**
 * Created by cristianoliveira on 08/05/15.
 */
public class Weather {

	private String condition;

	private Float temperatureMax;

	private Float temperatureMin;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Float getTemperatureMax() {
		return temperatureMax;
	}


	public void setTemperatureMax(Float temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public Float getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(Float temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

}
