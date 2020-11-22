package com.code.email_send;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App 
{
    public static void main( String[] args )
    {
        
    	String message= " Have a nice day ";
    	String subject = "Greetings";
    	
    	// add the email id of recipient
    	String to = "";
    	
    	//add the email id of the sender
    	String from = "";

    		sendEmail(message , subject , to , from);
    	
    }

	private static void sendEmail(String message, String subject, String to, String from) {

		//get the system properties in a variable 
		Properties properties = System.getProperties();
		
		//configure the properties 
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth", "true");
		
		//get the session object 
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				//add your email id with the credentials as parameters below 
				//note: this will fail if your credentials arent valid
				//for example : PasswordAuthentication("abc@gmail.com","abc123");
				return new PasswordAuthentication("","");
			}
		});
		
		//session.setDebug(true);
		MimeMessage m = new MimeMessage(session);
		try {
		m.setFrom(from);
		
		
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		
		m.setSubject(subject);
		
		m.setText(message);
		
		
		Transport.send(m);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
