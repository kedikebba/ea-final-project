package ea.finalproject.notificationservice.service;

import com.google.gson.Gson;
import ea.finalproject.notificationservice.Model.PaymentInformation;
import ea.finalproject.notificationservice.repository.PaymentInformationRepository;
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

    @Autowired
    PaymentInformationRepository paymentInformationRepository;

    @KafkaListener(topics = "Subscription", groupId = "group_id")
    public void consume(String message) throws IOException {
        Gson gson = new Gson();
        PaymentInformation paymentInformation = gson.fromJson(message, PaymentInformation.class);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(paymentInformation.getEmail());
            mimeMessageHelper.setSubject("Your mobile subscription has been received and processed successfully");
            mimeMessageHelper.setText("Dear " + paymentInformation.getFirstName()
                    + "\n Your subscription for " + paymentInformation.getPlan()
                    + "\n  at a cost of USD." + paymentInformation.getAmount()
                    + " has been processed successfully on " + paymentInformation.getSubscriptionDate()
                    + " and will expire on " + paymentInformation.getExpiryDate()
                    + "\n\n Thank you for your continued support \n\nEA fanatics");


        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
        paymentInformationRepository.save(paymentInformation);

    }
}
