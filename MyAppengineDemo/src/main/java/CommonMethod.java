import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class CommonMethod {
	public static JSONObject JSONObjectInputStream(InputStream in) throws IOException {
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
		JSONObject json = new JSONObject();
		while ((line = br.readLine()) != null) {
			try {
				json = new JSONObject(line);
			} catch (JSONException e) {
				return null;
			}
		}
		
		return json;
	}
}
