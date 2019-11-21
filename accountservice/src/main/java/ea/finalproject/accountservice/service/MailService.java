package ea.finalproject.accountservice.service;

import ea.finalproject.accountservice.model.Email;
import ea.finalproject.accountservice.model.User;

public interface MailService {
    public void sendMail(Email email) throws Exception;
    public void sendMail(User receiver, String subject, String message);
}