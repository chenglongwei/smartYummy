package com.smartYummy.service;

import com.smartYummy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author chenglongwei
 * @version 1.0
 * @since 2016-05-22
 * <p/>
 * Async email service, to send notification email when create an order and when order is starting.
 */
@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(User user, String subject, String text) throws MailException, InterruptedException {
        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject(subject);
        mail.setText(text);
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }
}