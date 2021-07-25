package com.vjti.service.impl;

import com.vjti.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by vishwajit_gaikwad on 18/7/21.
 */
@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String message, String to, String subject) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(message);

        javaMailSender.send(msg);

    }
}
