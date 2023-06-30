package com.practice.shop.service.impl;

import com.practice.shop.model.lesson.Lesson;
import com.practice.shop.service.EmailService;
import com.practice.shop.web.controller.security.userdetails.CustomUserDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    @Override
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

    @SneakyThrows
    @Override
    public void studentAddNote(Lesson lesson, String to, String subject) {
        MimeMessage message = mailSender.createMimeMessage();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String htmlMsg = "<p>Student " + userDetails.getUsername() + "has left note to lesson " +
                " " + lesson.getDateTime() + "</p>" +
                "<p> " + lesson.getNote() + "</p>";
        message.setContent(htmlMsg, "text/html");
        helper.setTo(to);
        helper.setSubject(subject);
        mailSender.send(message);
    }
    @SneakyThrows
    @Override
    public void cancelByTeacher(Lesson lesson, String to, String subject) {
        MimeMessage message = mailSender.createMimeMessage();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String htmlMsg = "<p>Teacher " + userDetails.getUsername() + "has cancelled lesson " +
                " " + lesson.getDateTime() + "</p>" ;
        message.setContent(htmlMsg, "text/html");
        helper.setTo(to);
        helper.setSubject(subject);
        mailSender.send(message);
    }


    @SneakyThrows
    @Override
    public void cancelByStudent(Lesson lesson, String to, String subject) {
        MimeMessage message = mailSender.createMimeMessage();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String htmlMsg = "<p>Student " + userDetails.getUsername() + "has cancelled lesson " +
                " " + lesson.getDateTime() + "</p>" ;
        message.setContent(htmlMsg, "text/html");
        helper.setTo(to);
        helper.setSubject(subject);
        mailSender.send(message);
    }
}
