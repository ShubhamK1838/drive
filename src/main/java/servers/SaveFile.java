package servers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.hibernate.Session;
import org.hibernate.Transaction;

import database.FactoryProvider;
import entities.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import myutil.Storage;
import jakarta.servlet.http.*;




@MultipartConfig
public class SaveFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user=(User)request.getSession().getAttribute("user");
		if(user==null) 
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		Part part=request.getPart("file"); 
		
		entities.File efile=new entities.File(); 
		efile.setAddedDate(new java.util.Date());
		efile.setFileName(part.getSubmittedFileName());
		efile.setFileType(part.getContentType());
		efile.setSize(myutil.FileSizeFormatter.formatSize(part.getSize()) );	
		efile.setUser(user);
		

				
		
		Storage sr=new Storage(efile); 
		sr.fileSave(part.getInputStream());
		
		
		
				
		
		
		
		
		
		response.getWriter().append(" File is Uploaded"); 
		
		request.getRequestDispatcher("MyFiles.jsp").forward(request, response);

		
		
	}

}
