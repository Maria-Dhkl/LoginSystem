package com.users;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
	
	public String getRandom() {
		Random rand = new Random();
		int number = rand.nextInt(99999);
		
		return String.format("%05d", number);
	}
	
	public boolean SendEmail(User user) {
		boolean test=false;
		
		String senderEmail = "yourEmail";
		String password = "youPassword";
		String receiverEmail = user.getEmail();
		
		//Properties for smtp mail server
		try {
			Properties property = new Properties();
			property.setProperty("mail.smtp.host", "smtp.gmail.com");
			property.setProperty("mail.smtp.port", "587");
			property.setProperty("mail.smtp.auth", "true");
			property.setProperty("mail.smtp.starttls.enable", "true");
			property.setProperty("mail.smtp.socketFactory.port", "587");
			property.setProperty("mail.smtp.socketFactory.port", "javax.net.ssl.SSLSocketFactory");
			
			//get session
			Session session = Session.getInstance(property, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, password);
				}
			});
			
			Message message = new MimeMessage(session);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
			message.setFrom(new InternetAddress(senderEmail));
			message.setSubject("User email verification");
			message.setText("Registered successfully! Please verify your email address using the code: "+"/n"+user.getCode());
			
			Transport.send(message);
			test = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return test;
	}
}
