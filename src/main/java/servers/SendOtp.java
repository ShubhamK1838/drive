package servers;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import myutil.SendEmail;

/**
 * Servlet implementation class SendOtp
 */
public class SendOtp extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userMail=request.getParameter("email"); 
		Integer otp=getOTP(); 
		
		System.out.println(" the request is recived for send mail "+ userMail); 
		
		SendEmail mail=new SendEmail() ; 
		mail.setReceiver(userMail);
		mail.setSubject("Cloud Server User Email Verifaction...");
		mail.setMessage("Your OTP IS " + otp);
		
		setInContext(userMail, otp);
		
		mail.start();
		
		
		
		
	}
	
	private  void setInContext(String mail, Integer otp)
	{
		Map<String,Integer> mailList=(Map<String,Integer>)getServletContext().getAttribute("mailList"); 
		
		if(mailList==null || mailList.size()<=0) 
		{
			mailList=new Hashtable<String, Integer>(); 
		}
		
		mailList.put(mail, otp)  ; 
		getServletContext().setAttribute("mailList", mailList);
	}

	private int getOTP()
	{
		return new Random().nextInt(1111, 9999);
	}


}
