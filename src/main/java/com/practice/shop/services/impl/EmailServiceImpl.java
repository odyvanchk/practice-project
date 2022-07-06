package com.practice.shop.services.impl;

import com.practice.shop.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    public void sendConfirmEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String htmlMsg = String.format("<form method=\"get\" action=\"http://localhost:8080/api/v1/users/confirm/%s\"> " +
                "<p>Press the button to confirm email</p>" +
                "<input type=\"submit\" value=\"Confirm Email\" /></form>", to);
        message.setContent(htmlMsg, "text/html");
        helper.setTo(to);
        helper.setSubject(subject);
        mailSender.send(message);
    }
}
