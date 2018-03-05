
import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Mail
 */
@WebServlet("/Mail")
public class Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mail;
		String[] receivers = {"tollodim@gmail.com", "adim1@mail.bg"};

	
		try {
			postMail( receivers, "Java Web App Test", "Just a server test", "tollodim@yahoo.com");
			mail = "Success";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			mail = "Problem sending mail: " + e;
		}
		response.getWriter().append(mail);
	}

	public void postMail( String recipients[ ], String subject, String message , String from) throws MessagingException
	{
	    boolean debug = false;
	 
	     //Set the host smtp address
	     Properties props = new Properties();
	     props.put("localhost", "localhost");
	 
	    // create some properties and get the default Session
	    Session session = Session.getDefaultInstance(props, null);
	    session.setDebug(debug);
	 
	    // create a message
	    Message msg = new MimeMessage(session);
	 
	    // set the from and to address
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);
	 
	    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
	    for (int i = 0; i < recipients.length; i++)
	    {
	        addressTo[i] = new InternetAddress(recipients[i]);
	    }
	    msg.setRecipients(Message.RecipientType.TO, addressTo);
	    
	 
	    // Optional : You can also set your custom headers in the Email if you Want
	    msg.addHeader("MyHeaderName", "myHeaderValue");
	 
	    // Setting the Subject and Content Type
	    msg.setSubject(subject);
	    msg.setContent(message, "text/plain");
	    Transport.send(msg);
	}
	
}
