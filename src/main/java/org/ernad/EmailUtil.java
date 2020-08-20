package org.ernad;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    public static void sendMail(String recepient, String emailMessage) throws Exception {
        System.out.println("Preparing to send email...");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // EDIT HOST
        properties.put("mail.smtp.port", "587"); // EDIT PORT

        String myEmailAccount = "test.email@gmail.com"; // EDIT YOUR SERVER EMAIL
        String password = "testpassword"; // SERVER EMAIL PW

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String myEmailAccount = "test.email@gmail.com"; // EDIT YOUR SERVER EMAIL
                String password = "testpassword"; // SERVER EMAIL PW
                return new PasswordAuthentication(myEmailAccount, password);
            }
        });

        Message message = prepareMessage(session, myEmailAccount, recepient, emailMessage);
        Transport.send(message);
        System.out.println("An email with the login data is sent to the mailbox!");
    }

    private static Message prepareMessage(Session session, String myEmailAccount, String recepient,
            String emailMessage) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Your email details");
            message.setText(emailMessage);
            return message;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
