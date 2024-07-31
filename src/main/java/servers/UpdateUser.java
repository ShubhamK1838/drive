package servers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import database.UserDao;
import dbhelper.SpContext;
import entities.User;
import entities.UserProfile;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;


@MultipartConfig
public class UpdateUser extends HttpServlet {
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		
		User user = (User) request.getSession().getAttribute("user");
		Part part=request.getPart("image"); 
		UserProfile image=null; 
		
		byte fileData[]=part.getInputStream().readAllBytes();
		
		
		if(part!=null && fileData.length>0)
		{
			image=new UserProfile(); 
			image.setImage(fileData);
			
		}
	

		if (user == null)
			request.getRequestDispatcher("login.jsp").forward(request, response);
		else {
			UserDao userDao = SpContext.context.getBean("userDao", UserDao.class);
			User s=user ; 
			s.setEmail(request.getParameter("Email"));
			s.setName(request.getParameter("Name"));
			s.setGender(request.getParameter("Gender"));
			s.setPassword(request.getParameter("Password"));
			if(image!=null)
			{
				s.setImage(image); 
			}
			
			userDao.update(s);
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		}
	}

}
