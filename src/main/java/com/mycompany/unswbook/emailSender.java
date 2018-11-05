/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unswbook;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;
/**
 *
 * @author yiqunrong
 */
public class emailSender {
    private Session session;
    private MimeMessage message;
    public emailSender(String receiver, String subject, String htmlContent,String textContent){
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", true); // added this line
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "username");
        props.put("mail.smtp.password", "password");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);
        session = Session.getInstance(props,null);
        message = new MimeMessage(session);
        try {
            InternetAddress from = new InternetAddress("unswbook88@gmail.com");
            message.setSubject(subject);
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));

            // Create a multi-part to combine the parts
            Multipart multipart = new MimeMultipart("alternative");
            // Create your text message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(textContent);
            // Add the text part to the multipart
            multipart.addBodyPart(messageBodyPart);
            // Create the html part
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html");
            // Add html part to multi part
            multipart.addBodyPart(messageBodyPart);
            // Associate multi-part with message
            message.setContent(multipart);

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }
        
    public boolean send(){
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "unswbook88", "xufengchishi");
            System.out.println("Transport: "+transport.toString());
            transport.sendMessage(message, message.getAllRecipients());
            return true;
            
            } catch (AddressException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
    }
}
