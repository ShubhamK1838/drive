package myutil;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends Thread {
	
	public  static void main(String ar[])
	{
		
		
		 SendEmail mail=new SendEmail(); 
		 mail.setReceiver("jadoo.8k@gmail.com");
		 mail.setSubject("Cloud Storage");
		 mail.setMessage("Testing Purpose " + new Date());
		 
		 mail.run(); 
	}

	private String receiver;
	private String message, subject; 

	
	public void run()
	{
		if(receiver==null || message==null || subject==null)
		{
			return ; 
		}
		sendMail(new String[] {receiver,subject,message}); 
	}
	public static void sendMail(String[] ar) {
		if(ar.length<=1) return ; 
		// Recipient's email ID needs to be mentioned.
		String to = ar[0];

		// Sender's email ID needs to be mentioned.
		String from = "shubhamkshirsagar1838@gmail.com";
		final String username =  from;  // Change accordingly
		final String password = "iznu kqia mfvz zfva"; // Change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(ar[1]);

			// Now set the actual message
			message.setText(ar[2]);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
