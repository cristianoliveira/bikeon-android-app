package cc.bikeon.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by cristianoliveira on 09/05/15.
 */
public class ParserUtil {

	public static String InputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader in = new BufferedReader(
						new InputStreamReader(inputStream));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}
}
