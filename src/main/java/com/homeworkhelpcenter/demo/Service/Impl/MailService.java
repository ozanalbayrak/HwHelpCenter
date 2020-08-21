package com.homeworkhelpcenter.demo.Service.Impl;

import com.homeworkhelpcenter.demo.Service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService implements IMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String body, String subject) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("mt.ozanalbayrak@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);
    }
}
