package servers;

import java.io.IOException;

import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestOne extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 response.setHeader("Access-Control-Allow-Origin", "*");
			User user= (User)request.getSession().getAttribute("user");  
			
			if(user==null)
			{
				System.out.println(" The user is not found " +user);
			}else
			{
				System.out.println(" the is found " +user);
			}
			
			response.setContentType("text/html" ); 
			
			response.getWriter().println(" This messag from server" ); 
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
