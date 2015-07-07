package cc.bikeon.app.services.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by cristianoliveira on 01/07/15.
 */
public class JsonTypeAdapter<T> extends TypeAdapter<T> {

    private TypeAdapter<T> delegate;
    private TypeAdapter<JsonElement> elementAdapter;

    public JsonTypeAdapter(TypeAdapter<T> delegate, TypeAdapter<JsonElement> elementAdapter){
        this.delegate = delegate;
        this.elementAdapter = elementAdapter;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        delegate.write(out, value);
    }

    @Override
    public T read(JsonReader in) throws IOException {

        JsonElement jsonElement = elementAdapter.read(in);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("cod") && jsonObject.get("cod").getAsInt() == 404) {
                throw new IllegalArgumentException(jsonObject.get("message").getAsString());
            }
        }

        return delegate.fromJsonTree(jsonElement);
    }
}
