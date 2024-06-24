package com.example.basic.util;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
                "leeyelim98@gmail.com", "nvmlsdwyjetgplzy");
    }
}
