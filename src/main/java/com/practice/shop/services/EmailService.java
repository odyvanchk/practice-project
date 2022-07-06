package com.practice.shop.services;

import javax.mail.MessagingException;

public interface EmailService {

    void sendConfirmEmail(String to, String subject, String text) throws MessagingException;
}
