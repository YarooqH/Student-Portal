package StudentPortal.src.model;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    public static void sendMail() throws MessagingException {
        // This is Java program to send a text email only to multiple people using an
        // array./
        // Setting up server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.port", 25);// 25 for plain text and 587 for encrypted information
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.transport.protocol", "smtp");

        // Preparing session to send email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ubit.studentportal@gmail.com", "ubit123456");// Sender's email and
                // return new PasswordAuthentication("yarooq1@gmail.com", "hello and welcome to
                // tech deals");// Sender's
                // email and
                // password
            }
        });

        // Message to send
        Message message = new MimeMessage(session);
        message.setSubject("Email from my prgram"); // Email subject
        message.setText("Hey there!\nYour account has been approved by the Admin!"); // Email text to be sent
        String[] emailAddresses = { "yarooq1@gmail.com" };// Recipients emails
        for (int i = 0; i < emailAddresses.length; i++) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddresses[i]));
        }
        // Sending email by transport class
        Transport.send(message);
        System.out.println("Email successfully sent!!!");
    }
}