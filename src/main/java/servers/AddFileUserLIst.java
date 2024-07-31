package servers;

import java.io.IOException;

import database.FileDao;
import database.UserDao;
import dbhelper.SpContext;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myutil.Storage;


public class AddFileUserLIst extends HttpServlet {
	
       
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid=request.getParameter("userid"), 
				fileid=request.getParameter("fileid"); 
		
		FileDao fileDao= SpContext.context.getBean("fileDao", FileDao.class);
		UserDao userDao= SpContext.context.getBean("userDao", UserDao.class);
		
		entities.File file =fileDao.getFile(Integer.parseInt(fileid)); 
		
		User rec=userDao.getUser(Integer.parseInt(userid)); 
		
		Storage storage=new Storage(file ); 
		
		storage.share(rec);
		System.out.println(file +" \n"+ rec); 
		response.setStatus(HttpServletResponse.SC_OK);
		
		
		
		
	}


}
