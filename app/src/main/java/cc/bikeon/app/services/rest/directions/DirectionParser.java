package cc.bikeon.app.services.rest.directions;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonObject;

/**
 * Created by cristianoliveira on 06/06/15.
 */
public class DirectionParser {

    public static LatLng parseToLatLng(JsonObject jsonObject) {
        return new LatLng(jsonObject.get("lat").getAsDouble(), jsonObject.get("lng").getAsDouble());
    }
}
