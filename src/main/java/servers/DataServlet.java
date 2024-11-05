package servers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import database.FileDao;
import dbhelper.SpContext;
import entities.File;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myutil.Storage;

import java.io.IOException;
import java.util.List;
import java.util.Base64;

@WebServlet("/data")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
		}
		FileDao dao = SpContext.context.getBean("fileDao", FileDao.class);

		List<File> list = dao.getUserFiles(user.getUserId());
		if (list == null || list.size() <= 0)
			response.sendRedirect("login.jsp");

		JsonArray jsonArray = new JsonArray();
		for (File ele : list) {
			JsonObject ob = new JsonObject();
			ob.addProperty("id", ele.getFileId());
			ob.addProperty("name", ele.getFileName());
			ob.addProperty("type", ele.getFileType().substring(0, ele.getFileType().toString().indexOf('/')));
			ob.addProperty("addedDate", ele.getAddedDate().toString());
			ob.addProperty("size", ele.getSize());
			ob.addProperty("url", "Resource?id="+ele.getFileId());

			jsonArray.add(ob);

		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		String jsonResponse = gson.toJson(jsonArray);

		System.out.println(jsonResponse + "\n\n" + jsonArray);

		response.getWriter().write(jsonResponse);
	}

	
}
