package com.practice.shop.service;

import javax.mail.MessagingException;

public interface EmailService {

    void sendConfirmEmail(String to, String subject, String text) throws MessagingException;
}
