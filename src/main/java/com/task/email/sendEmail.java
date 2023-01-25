package com.task.email;

import java.io.File;
import java.net.URI;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class sendEmail {
    private String username;
    private String password;
    private String body;
    private String dest;
    private final Properties prop;

    public sendEmail(String host, int port, String username, String password, StringBuilder body, String dest) {
        prop = new Properties();
        prop.put("mail.smtp.auth", true);
        // prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl", "no");
        // prop.put("mail.smtp.tls", "yes");

        this.body = body.toString();
        this.username = username;
        this.password = password;
        this.dest  = dest;
    }

    public sendEmail(String host, int port) {
        prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
    }

    // public static void main(String... args) {
    //     try {
    //         new sendEmail("smtp.mailtrap.io", 25, "87ba3d9555fae8", "91cb4379af43ed")
    //           .sendMail();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public void sendMail() throws Exception {

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("taskmanagersc@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.dest));
        message.setSubject("Task Purge Files");

        String msg = body;

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        String msgStyled = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";
        MimeBodyPart mimeBodyPartWithStyledText = new MimeBodyPart();
        mimeBodyPartWithStyledText.setContent(msgStyled, "text/html; charset=utf-8");

        //MimeBodyPart attachmentBodyPart = new MimeBodyPart();

       // attachmentBodyPart.attachFile(getFile());

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        multipart.addBodyPart(mimeBodyPartWithStyledText);
       // multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    // private File getFile() throws Exception {
    //     URI uri = this.getClass()
    //       .getClassLoader()
    //       .getResource("attachment.txt")
    //       .toURI();
    //     return new File(uri);
    // }
}