package servers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.Transaction;

import database.FactoryProvider;
import database.UserDao;
import dbhelper.SpContext;
import entities.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import myutil.Storage;

public class Login extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		doPost(request, response); 
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String relativePath = "uploadedFiles"; // the directory where you want to store files
		String absolutePath = getServletContext().getRealPath(relativePath);
		

		
		Storage.path=absolutePath; 
		
		if(request.getSession().getAttribute("user")!=null)
		{
			request.getRequestDispatcher("home.jsp").include(request, response);
		}
		String mail = request.getParameter("email");
		String pass = request.getParameter("password");

		if (mail == null || pass == null|| mail.length()<=0|| pass.length()<=0) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		UserDao dao = SpContext.context.getBean("userDao", UserDao.class);

		User user = dao.getUser(mail, pass);

		if (user == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
			
		ArrayList<HttpSession> sessionList; 
		ServletContext servletContext = request.getServletContext();
		
		ArrayList<HttpSession> lusers=(ArrayList<HttpSession>)servletContext.getAttribute("logged_users"); 
		
		if(lusers==null)
		{
			lusers=new ArrayList<HttpSession>(); 
		}

		
		HttpSession hs = request.getSession();
		hs.setAttribute("user", user);
		lusers.add(hs); 
		servletContext.setAttribute("logged_users", lusers);
		
		if(user.isVerified())
		{
			
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else 
		{
			request.getRequestDispatcher("emailverify.jsp").forward(request, response);
			
		}

	}
	

}
