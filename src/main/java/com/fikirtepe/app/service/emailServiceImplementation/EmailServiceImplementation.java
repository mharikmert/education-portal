package com.fikirtepe.app.service.emailServiceImplementation;

import com.fikirtepe.app.model.User;
import com.fikirtepe.app.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Component
@Service
public class EmailServiceImplementation implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImplementation.class);
    private final JavaMailSenderImpl javaMailSender;
    private final String email;
    private final String password;

    //injects javaMailSender for mailing capability
    @Autowired
    public JavaMailSender setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        javaMailSender.setUsername(email);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    //get email bean with qualifier annotation that is defined in email configuration class
    @Autowired
    public EmailServiceImplementation(JavaMailSenderImpl javaMailSender, @Qualifier("email") String email, @Qualifier("email_password") String password){
        this.javaMailSender = javaMailSender;
        this.email = email;
        this.password = password;
    }

    private Properties getMailProperties(){
        Properties properties = new Properties();
        properties.setProperty("mail.username",email);
        properties.setProperty("mail.password",password);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        return properties;
    }

    @Override
    public void sendRegistrationReceivedMail(User user) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe Öğrenci Sistemi Kayıt");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kaydınız başarıyla alınmıştır. Kayıt onayı için bilgilendirme yapılacaktır. \n\n" +
                "İyi günler."
        );
        try {
            javaMailSender.send(msg);
        }catch (MailException ex){
            logger.info(msg.toString());
        }
    }

    @Override
    public void sendRegistrationApprovedMail(User user) throws MailException{
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe Öğrenci Sistemi Kayıt Onay");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kaydınız onaylanmıştır, şifrenizi giriş ekranından oluşturup sisteme giriş yapabilirsiniz. \n\n" +
                "İyi günler."
        );
        try{
            javaMailSender.send(msg);
        }catch(MailException ex){
            logger.info(msg.toString());
        }

    }

    @Override
    public void sendRegistrationRejectedMail(User user) throws MailException{
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe Öğrenci Sistemi Kayıt Red");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kaydınız reddedilmiştir \n\n" + //rejection reason
                "İyi günler."
        );
        try{
            javaMailSender.send(msg);
        }catch(MailException ex){
            logger.info(msg.toString());
        }

    }
}
