package cc.bikeon.app.services.rest.weather;

/**
 * Created by cristianoliveira on 27/05/15.
 */
public class WeatherFormatter {

    /**
     *
     * Format weather temperature to pattern
     *
     * @param value Temperature value
     * @return string Formatted value
     */
    public static String formatCelsius(double value){
        StringBuilder weatherString = new StringBuilder();
        weatherString.append(String.format("%.0f", value));
        weatherString.append("˚C");

        return weatherString.toString();
    }
}
