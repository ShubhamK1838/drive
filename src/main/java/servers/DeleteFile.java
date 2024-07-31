package servers;

import java.io.File;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import database.FactoryProvider;
import database.FileDao;
import dbhelper.SpContext;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myutil.Storage;

public class DeleteFile extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		String file_id = request.getParameter("file_id");
		FileDao dao = SpContext.context.getBean("fileDao", FileDao.class);

		entities.File fl = dao.getFile(Integer.parseInt(file_id));
		Storage st=new Storage(fl); 
		
		st.fileDelete();
		
		request.getRequestDispatcher("MyFiles.jsp").forward(request, response);

	}

}
