package cc.bikeon.app.services.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.annotation.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by cristianoliveira on 01/07/15.
 */

@RunWith(MockitoJUnitRunner.class)
@Config(manifest= Config.NONE)
public class JsonTypeAdapterTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenJsonHas404CodeItShouldRaiseIllegalArgumentException() throws IOException {
        // given
        String codeError = "404";
        JsonObject jsonWithCodeError = new JsonObject();
        jsonWithCodeError.addProperty("cod", codeError);
        jsonWithCodeError.addProperty("message", "some message");

        TypeAdapter<JsonElement> stubAdapter = new Gson().getAdapter(JsonElement.class);
        TypeAdapter<JsonElement> stubElementAdapter = new Gson().getAdapter(JsonElement.class);

        JsonTypeAdapter jsonTypeAdapter = new JsonTypeAdapter(stubAdapter, stubElementAdapter);

        // when
        jsonTypeAdapter.read(new JsonReader(new StringReader(jsonWithCodeError.toString())));

        // then raise IllegalArgumentException
        fail("Must raise error IllegalArgumentException");
    }

    @Test
    public void whenJsonHasNo404CodeItShouldReturnJsonObject() throws IOException {
        // given
        String expectedValue = "some value";
        JsonObject jsonWithCodeError = new JsonObject();
        jsonWithCodeError.addProperty("some_property", expectedValue);

        TypeAdapter<StubDomain> stubAdapter = new Gson().getAdapter(StubDomain.class);
        TypeAdapter<JsonElement> stubElementAdapter = new Gson().getAdapter(JsonElement.class);

        JsonTypeAdapter<StubDomain> jsonTypeAdapter =
                new JsonTypeAdapter(stubAdapter, stubElementAdapter);

        // when
        StubDomain result =
                jsonTypeAdapter.read(new JsonReader(new StringReader(jsonWithCodeError.toString())));

        // then
        assertEquals(expectedValue, result.someProperty);
    }

    private class StubDomain {
        @SerializedName("some_property")
        public String someProperty;
    }

}
