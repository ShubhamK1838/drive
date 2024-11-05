package servers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.persistence.Entity;

import database.FileDao;
import dbhelper.SpContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myutil.Storage;

@WebServlet("/video")
public class Video extends HttpServlet {
	
	

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 String rangeHeader =request.getHeader("Range"); 
    	 String id =request.getParameter("id"); 
    	 if (id == null || id.isEmpty()) {
             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing video ID");
             return;
         }
    	 
    	 
    	 String[] range=rangeHeader.substring(0,rangeHeader.length()).split("-"); 
    	 long startIndex=Long.parseLong(range[0]), endIndex=0; 
    	 
    	 FileDao dao = SpContext.context.getBean("fileDao", FileDao.class);
         entities.File fl=dao.getFile(Integer.parseInt(id)); 
         System.out.println(fl);
         Storage st = new Storage(fl);
         File file = st.getIoFile();
         
    	 endIndex=range.length>1?Long.parseLong(range[1]):file.length();  
    	 
    	   if (!file.exists()) {
               response.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
               return;
           }
        
        
        
       
     
        
        response.setContentType(fl.getFileType());
        response.setContentLengthLong(file.length());
        
        try (FileInputStream in = st.getFileInputStream(); 
             OutputStream out = response.getOutputStream()) {
            
            byte[] buffer = new byte[4096*4]; // Buffer size
            int bytesRead;
            
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                out.flush(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error streaming video");
        }
        System.out.println("File Prview"+ file ); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
