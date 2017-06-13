
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.JsonObject;

import entities.Student;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.print("Hello Bao Quang Tu!");

			Student stu = new Student();
			stu.setId("ST" + System.nanoTime());
			String name = null;
			int age = 0;
			String address = null;

			JSONObject jsonObject = CommonMethod.JSONObjectInputStream(request.getInputStream());

			if (jsonObject.has("name")) {
				name = jsonObject.getString("name");
			}
			
			if (jsonObject.has("age")) {
				age = Integer.parseInt(jsonObject.getString("age"));
			}
			
			if (jsonObject.has("address")) {
				address = jsonObject.getString("address");
			}

			stu.setName(name);
			stu.setAge(age);
			stu.setAddress(address);

			boolean isSuccess = !ofy().save().entities(stu).now().isEmpty();

			if (!isSuccess) {
				out.println("Error save data!");
			}

			out.println("Save Success!" + name);

			response.setStatus(200);
		} catch (JSONException e) {

		}
	}

}
