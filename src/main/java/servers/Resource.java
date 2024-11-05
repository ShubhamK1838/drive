package servers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import database.FileDao;
import dbhelper.SpContext;
import entities.File;
import myutil.Storage;

/**
 * Servlet implementation class Resource
 */
public class Resource extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Request received"); 
		int id=Integer.parseInt(request.getParameter("id")); 
		
			FileDao dao=SpContext.context.getBean("fileDao",FileDao.class); 
			File file=dao.getFile(id);
			Storage st=new Storage(file);
			
			FileInputStream io=st.getFileInputStream(); 
			
			response.setContentType(file.getFileType());
			response.setContentLength((int)st.getIoFile().length());
			
		
			
			OutputStream ot=response.getOutputStream(); 
			
			byte tmp[]=new byte[1024*8]; 
			while(io.read(tmp)!=-1)
			{
				ot.write(tmp);
				ot.flush();
			
			}
			
			ot.close();
			io.close();
			
			
			
	
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
