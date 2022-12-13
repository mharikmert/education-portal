package portal.backend.app.service.emailServiceImplementation;

import portal.backend.app.model.User;
import portal.backend.app.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class EmailServiceImplementation implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImplementation.class);
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String email;

    // injects javaMailSender from application context
    @Autowired
    public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendRegistrationReceivedMail(User user) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe Öğrenci Sistemi Kayıt");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kaydınız başarıyla alınmıştır. Kayıt onayı için bilgilendirme yapılacaktır. \n\n" +
                "İyi günler.");
        try {
            javaMailSender.send(msg);
        } catch (MailException ex) {
            logger.info(msg.toString());
        }
    }

    @Override
    public void sendRegistrationApprovedMail(User user) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe Öğrenci Sistemi Kayıt Onay");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kaydınız onaylanmıştır, şifrenizi giriş ekranından oluşturup sisteme giriş yapabilirsiniz. \n\n" +
                "İyi günler.");
        try {
            javaMailSender.send(msg);
        } catch (MailException ex) {
            logger.info(msg.toString());
        }

    }

    @Override
    public void sendRegistrationRejectedMail(User user) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe Öğrenci Sistemi Kayıt Red");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kaydınız reddedilmiştir \n\n" + // rejection reason
                "İyi günler.");
        try {
            javaMailSender.send(msg);
        } catch (MailException ex) {
            logger.info(msg.toString());
        }

    }
}
