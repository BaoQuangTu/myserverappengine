

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.googlecode.objectify.cmd.Query;

import entities.Student;

/**
 * Servlet implementation class GetListUserServlet
 */
public class GetListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(GetListUserServlet.class.getName());
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			JSONObject jsonObject = CommonMethod.JSONObjectInputStream(request.getInputStream());
			
			int age = 22;
			int page = 0;
			int count = 10;
			
			
			if (!jsonObject.has("age")) {
				response.getWriter().println("error!");
				response.setStatus(500);
				return;
			} else {
				age = jsonObject.getInt("age");
			}
			
			if (jsonObject.has("page")) {
				page = jsonObject.getInt("page");
			}
			
			if (jsonObject.has("count")) {
				count = jsonObject.getInt("count");
			}
			
			List<Student> listStudents = new ArrayList<>();
			
			Query<Student> loadType = ofy().load().type(Student.class).hybrid(false);
			Query<Student> query = null;
			
			query = loadType.filter("age =", age);
			
			listStudents = query.offset(page * count).limit(count).list();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(new Gson().toJson(listStudents));
			response.setStatus(200);
		} catch (JSONException e) {

		}
	}
}
