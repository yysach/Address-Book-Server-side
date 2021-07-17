package com.example.boot_app.service.Impl;

import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;
import org.springframework.stereotype.Service;


@Service
public class EmailService{

    private Properties props;


    protected void setProperty(){
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465"); 
    }

    protected Session getSession(String from,String password){
        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {    
                return new PasswordAuthentication(from,password);  
            }
        });
        return session;
    }

    public String composeMail(String from,String to,String password){

    //compose the message  
      try{  
        setProperty();
        MimeMessage message = new MimeMessage(getSession(from,password));  
        message.setFrom(new InternetAddress(from));  
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
        message.setSubject("One Time Password");  
        int random_int = (int)Math.floor(Math.random()*(999999-100000+1)+100000);
        Integer otp = Integer.valueOf(random_int);
        message.setText("Your one time password "+otp.toString());  
 
        // Send message  
        Transport.send(message);  
        System.out.println("message sent successfully....");  
        return otp.toString();
 
     }catch (MessagingException mex) {
         mex.printStackTrace();
        }
        return "";
    }

}