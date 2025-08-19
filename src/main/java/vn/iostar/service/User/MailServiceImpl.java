package vn.iostar.service.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import vn.iostar.model.MailModel;
import vn.iostar.service.IMailService;

@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SpringTemplateEngine templateEngine;


    @Override
    public void sendHtmlMail(MailModel mailModel, String templateName) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        Context context = new Context();
        context.setVariables(mailModel.getProps());

        String html = templateEngine.process(templateName, context);

        helper.setTo(mailModel.getTo());
        helper.setSubject(mailModel.getSubject());
        helper.setText(html, true);

        javaMailSender.send(message);
    }
}
