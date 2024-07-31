package servers;

import java.io.File;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import database.FactoryProvider;
import database.UserDao;
import dbhelper.SpContext;
import entities.User;
import entities.UserProfile;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class Signup extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getParameter("name"));

		String parameters[] = new String[] { "name", "email", "password", "gender" };

		String para = null;
		Part part = request.getPart("image");

		for (int i = 0; i < parameters.length; i++) {

			para = request.getParameter(parameters[i]);
			if (para == null || part == null) {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} else {

			}
			parameters[i] = para;

		}

		User user = new User();
		user.setName(parameters[0]);
		user.setEmail(parameters[1]);
		user.setPassword(parameters[2]);
		user.setGender(parameters[3]);
		UserProfile up = new UserProfile();
		byte dt[]=new byte[(int)part.getSize()];
//		byte[] bytes = part.getInputStream().readAllBytes();
		part.getInputStream().read(dt);
		up.setImage(dt);
		user.setImage(up);

		SpContext.context.getBean("userDao", UserDao.class).add(user);

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);

	}

}
