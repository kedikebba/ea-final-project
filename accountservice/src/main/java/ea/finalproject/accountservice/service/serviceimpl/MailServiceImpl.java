package ea.finalproject.accountservice.service.serviceimpl;

import ea.finalproject.accountservice.model.Email;
import ea.finalproject.accountservice.model.User;
import ea.finalproject.accountservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    private final String MailSender = "ea.fanatics2019@gmail.com";
    private final String MailPassword = "yzsshujzojkldrzq";

    @Autowired
    private JavaMailSender sender;

    public void sendMail(Email email) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.office365.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailSender, MailPassword);
            }
        });
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(MailSender, false));

        msg.setRecipients(Message.RecipientType.TO, email.getReceivers());
        msg.setSubject(email.getSubject());
        msg.setContent(email.getMessage(), "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(email.getMessage(), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        if(email.getFilePath()!=null) {
            attachPart.attachFile(email.getFilePath());
            multipart.addBodyPart(attachPart);
        }
        msg.setContent(multipart);
        Transport.send(msg);

        System.out.println("Mail sent");
    }

    @Override
    public void sendMail(User receiver, String subject, String message) {
        Email email = new Email();
        email.addReceiver(receiver.getEmail());
        email.setMessage(message);
        email.setSubject(subject);
        try {
            sendMail(email);
            System.out.println("Mail sent to "+receiver.getFirstName()+" successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Mail sending failed!!!");
        }
    }
}