package com.practice.shop.service;


import com.practice.shop.model.lesson.Lesson;
import jakarta.mail.MessagingException;
import lombok.SneakyThrows;

public interface EmailService {

    void sendConfirmEmail(String to, String subject, String text) throws MessagingException;

    @SneakyThrows
    void studentAddNote(Lesson lesson, String to, String subject);
}
