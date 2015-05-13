package cc.bikeon.app.services.weather;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cristianoliveira on 12/05/15.
 */
public class Weather {

    private String id;

    private String main;

    private String description;

    private String incon;

    public String getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIncon() {
        return incon;
    }

}
