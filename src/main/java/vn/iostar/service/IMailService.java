package vn.iostar.service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import vn.iostar.model.MailModel;


// location receive information your emil and your .html
@Service
public interface IMailService {
    // mailModel -> value return, Template -> key return for template here is: client.html
    void sendHtmlMail(MailModel mailModel, String templateName) throws MessagingException;
}
