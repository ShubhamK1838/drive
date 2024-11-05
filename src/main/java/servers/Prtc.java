package servers; 
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import org.json.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import myutil.Storage;
import database.FileDao;
import dbhelper.SpContext;

public class Prtc extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Record the start time
        long startTime = System.nanoTime();
        
        // Path to the video file
        String fileid = req.getParameter("id");
        
        FileDao dao = SpContext.context.getBean("fileDao", FileDao.class);
        Storage st = new Storage(dao.getFile(Integer.parseInt(fileid)));
        
        String videoPath = st.getIoFile().toString();
        
        // Read the video file into a byte array
        byte[] videoBytes = Files.readAllBytes(Paths.get(videoPath));
        
        // Encode the byte array into a base64 string
        String base64Video = Base64.getEncoder().encodeToString(videoBytes);
        
        // Create a JSON response containing the base64 video data
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("video", base64Video);
        
        // Set content type to JSON
        resp.setContentType("application/json");
        
        // Write JSON response
        resp.getWriter().write(jsonResponse.toString());
        
        // Record the end time
        long endTime = System.nanoTime();
        
        // Calculate and log the duration
        long duration = endTime - startTime; // Duration in nanoseconds
        System.out.println("Time taken to process and send the data: " + duration / 1_000_000 + " ms");
    }
}
