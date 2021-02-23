package com.fikirtepe.app.Service.EmailServiceImplementation;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class EmailServiceImplementation implements EmailService {

    private JavaMailSender javaMailSender;
    private final String email;

    //injects javaMailSender for mailing capability
    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    //get email bean with qualifier annotation that is defined in email configuration class
    @Autowired
    public EmailServiceImplementation(@Qualifier("email") String email){
        this.email = email;
    }
    @Override
    public void sendRegistrationEmail(User user) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(email);
        msg.setTo(user.getEmail());
        msg.setSubject("Fikirtepe Öğrenci Sistemi Kayıt");
        msg.setText("Sevgili " + user.getFirstName() + " " + user.getLastName() + "\n\n" +
                "Kaydınız başarıyla alınmıştır. Kayıt onayı için bilgilendirme yapılacaktır. \n\n" +
                "İyi günler."
        );
        javaMailSender.send(msg);
    }
}
