package ea.finalproject.accountservice;

import ea.finalproject.accountservice.model.Email;
import ea.finalproject.accountservice.service.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.security.GeneralSecurityException;

@SpringBootApplication
@EnableCaching
public class AccountserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountserviceApplication.class, args);
        //ApplicationContext context = SpringApplication.run(AccountserviceApplication.class, args);
       // sendMailCheck(context);
 }
//
//    private static void sendMailCheck(ApplicationContext context) {
//        MailService mailService = context.getBean(MailService.class);
//        try {
//            Email email = new Email();
//            email.setSubject("Phone Bill Payment System");
//            email.setMessage("Your payment has been successfull!!");
//            email.addReceiver("sarose301@gmail.com");
//            email.addReceiver("s_roses@live.com");
//            mailService.sendMail(email);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Bean
    public JavaMailSender getJavaMailSender() throws GeneralSecurityException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        return mailSender;
    }}
