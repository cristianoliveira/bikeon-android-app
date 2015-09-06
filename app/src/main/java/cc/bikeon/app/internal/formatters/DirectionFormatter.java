package cc.bikeon.app.internal.formatters;

import android.location.Location;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Responsible for format a given location
 * Created by cristianoliveira on 31/05/15.
 */
public class DirectionFormatter {

    private final static String CHARSET = "UTF-8";

    /**
     *
     * Parse the location to a URL like format
     *
     * @param location
     * @return String formatted location by coma
     */
    public static String format(Location location)
    {
        return String.format("%s,%s", location.getLatitude(), location.getLongitude());
    }

    /**
     *
     * Parse the string to a URL like format (UTF-8)
     *
     * @param location name
     * @return String formatted location by coma
     */
    public static String format(String location) throws UnsupportedEncodingException {
        return URLDecoder.decode(location, CHARSET);
    }
}
