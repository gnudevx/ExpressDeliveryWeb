package vn.iostar.service.User;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.entity.User;
import vn.iostar.model.ClientSdi;
import vn.iostar.model.MailModel;
import vn.iostar.service.IClientService;
import vn.iostar.service.IMailService;
import vn.iostar.utils.Const;
import vn.iostar.utils.DataUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    MailServiceImpl mailService;
    @Override
    public String create(User clientSdi) {
        try {
            MailModel dataMail = new MailModel();
            dataMail.setTo(clientSdi.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);
            Map<String, Object> props = new HashMap<>();
            props.put("name", clientSdi.getFullName());
            props.put("password", DataUtils.generateTempPwd(6));
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return (String)props.get("password");
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
        return null;
    }
}
