package servers;

import java.io.IOException;
import java.util.Map;

import database.UserDao;
import dbhelper.SpContext;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateEmailVerification
 */
public class ValidateEmailVerification extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		String email=request.getParameter("email"); 
		String code=request.getParameter("code"); 
		Map<String, Integer> map=(Map<String,Integer>)getServletContext().getAttribute("mailList"); 
		
		System.out.println(map+" "+ email+" "+ code); 
		if(map==null || map.size()<=0 || code==null || email==null || code.length()<=0 || email.length()<=0)
		{
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}else
		{
			User user = (User)request.getSession().getAttribute("user"); 
			if(map.get(email)==Integer.parseInt(code) && user!=null)
			{
				UserDao dao = SpContext.context.getBean("userDao", UserDao.class);

				
				user.setVerified(true);
				dao.update(user);
				response.setStatus(HttpServletResponse.SC_OK);
				
				
			}else 
			{
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
		
		
		
		
	
	}

	
	

}
