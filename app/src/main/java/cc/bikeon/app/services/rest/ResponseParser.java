package cc.bikeon.app.services.rest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import retrofit.client.Response;

/**
 * Created by cristianoliveira on 26/05/15.
 */
public class ResponseParser {

    /**
     *
     * Method to parser Response from Rest to bitmap
     *
     * @param response
     * @return Bitmap from Body of response
     * @throws IOException can raise this exception when parse body to InputStream
     */
    public Bitmap parseToBitmap(Response response) throws IOException {
        InputStream in = response.getBody().in();
        return BitmapFactory.decodeStream(in);
    }

}
