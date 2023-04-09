package com.practice.shop.service;


import jakarta.mail.MessagingException;

public interface EmailService {

    void sendConfirmEmail(String to, String subject, String text) throws MessagingException;
}
