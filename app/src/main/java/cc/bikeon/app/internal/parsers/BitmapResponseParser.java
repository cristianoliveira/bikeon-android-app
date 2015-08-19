package cc.bikeon.app.internal.parsers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import retrofit.client.Response;

/**
 * Created by cristianoliveira on 26/05/15.
 */
public class BitmapResponseParser implements Parser<Bitmap, Response> {

    /**
     * Method to parser Response from Rest to bitmap
     *
     * @param response
     * @return Bitmap from Body of response
     * @throws IOException can raise this exception when parse body to InputStream
     */
    @Override
    public Bitmap parse(Response response) {
        InputStream in = null;
        try {
            in = response.getBody().in();
            return BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            return null;
        }
    }
}
