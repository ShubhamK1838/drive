package servers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import database.FileDao;
import dbhelper.SpContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myutil.Storage;

//@WebServlet("/video")
public class Video extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        
        if (id == null || id.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing video ID");
            return;
        }
        
        FileDao dao = SpContext.context.getBean("fileDao", FileDao.class);
        Storage st = new Storage(dao.getFile(Integer.parseInt(id)));
        File file = st.getIoFile();
        
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
            return;
        }
        
        response.setContentType("video/mp4");
        response.setContentLength((int) file.length());
        
        try (FileInputStream in = st.getFileInputStream(); 
             OutputStream out = response.getOutputStream()) {
            
            byte[] buffer = new byte[4096*4]; // Buffer size
            int bytesRead;
            
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                out.flush(); // Ensure the data is sent immediately
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
