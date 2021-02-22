package com.fikirtepe.app.Service.EmailServiceImplementation;

import com.fikirtepe.app.Service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class EmailServiceImplementation implements EmailService {

    private JavaMailSender javaMailSender;

    //injects javaMailSender for mailing capability
    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    //trial mailing method
    @Override
    public void sendEmail() throws MailException{
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("emailfrom@gmail.com");
        msg.setTo("toemail@gmail.com");
        msg.setText("spring boot email testing");
        msg.setSubject("subject");
        javaMailSender.send(msg);
    }
}
