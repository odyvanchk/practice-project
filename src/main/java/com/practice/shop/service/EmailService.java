package com.practice.shop.service;


import com.practice.shop.model.lesson.Lesson;
import jakarta.mail.MessagingException;
import lombok.SneakyThrows;

public interface EmailService {

    void sendConfirmEmail(String to, String subject, String text) throws MessagingException;

    @SneakyThrows
    void cancelByTeacher(Lesson lesson, String to, String subject);

    @SneakyThrows
    void studentAddNote(Lesson lesson, String to, String subject);

    @SneakyThrows
    void cancelByStudent(Lesson lesson, String to, String subject);
}
