package servers;

import java.io.IOException;
import java.util.List;

import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=null; 
		String user_id= request.getParameter("user_id");

		
		if(user_id==null || user_id.length()<=0)
		session = request.getSession();
		else
		{
			List<HttpSession> list= (List<HttpSession>)request.getServletContext().getAttribute("logged_users"); 
 
			
			for(int i=0; i<list.size(); i++)
			{
				
				HttpSession sn=list.get(i); 
				User user= (User)sn.getAttribute("user"); 
				
				if(sn.getId().equals(user_id))
				{
					sn.invalidate();
					list.remove(i);  
					request.getServletContext().setAttribute("logged_users", list);
					System.out.println(" Logged Out :" +user.getName()+ " " + user.getUserId()); 
					System.out.println(" Logged Users " + list.size());  
					return ; 
				}
				
			}
		}
		if (session != null)
			session.invalidate();

		

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("inside the post ");
		doGet(request, response);
	}

}
