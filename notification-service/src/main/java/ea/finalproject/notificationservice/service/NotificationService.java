package ea.finalproject.notificationservice.service;

import com.google.gson.Gson;
import ea.finalproject.notificationservice.Model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class NotificationService {

    @Autowired
    JavaMailSender javaMailSender;

    @KafkaListener(topics = "Subscription", groupId = "group_id")
    public void consume(String message) throws IOException {
        Gson gson = new Gson();
        Notification notification = gson.fromJson(message, Notification.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(notification.getEmail());
            mimeMessageHelper.setSubject("Your mobile subscription has been received and processed successfully");
            mimeMessageHelper.setText("Dear " + notification.getFirstName()
                    + "\n Your subscription for " + notification.getPackageSubscribedFor()
                    + " has been processed successfully on " + notification.getDateOfSubscription()
                    + " and will expire on " + notification.getDateOfExpiry()
                    + "\n Thank you \n\nEA fanatics");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);

    }
}
