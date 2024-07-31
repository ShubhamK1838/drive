package servers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import database.FileDao;
import dbhelper.SpContext;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myutil.Storage;

@WebServlet("/download")
public class FileDownload extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String file_name = request.getParameter("fileid");
		if (user == null || file_name == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		FileDao fileDao = SpContext.context.getBean("fileDao", FileDao.class);
		Storage st=new Storage(fileDao.getFile(Integer.parseInt(file_name))); 
		
		File downloadFile =st.getIoFile(); 

		if (!downloadFile.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		FileInputStream inStream = new FileInputStream(downloadFile);

		// Get MIME type of the file
		String mimeType = getServletContext().getMimeType(downloadFile.toString());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		response.setContentType(mimeType);
		response.setContentLengthLong(downloadFile.length());

		// Force download
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead;

		if (downloadFile.length() < Integer.MAX_VALUE - 6) {
				
			outStream.write(inStream.readAllBytes());
			
		} else {

			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);

			}

		}
		System.out.println( " Download Request From " + user.getName() + " Resource:" + downloadFile);
		inStream.close();
		outStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
