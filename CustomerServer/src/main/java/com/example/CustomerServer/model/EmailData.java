package com.example.CustomerServer.model;

import lombok.Data;

@Data
public class EmailData {
    private String toEmail;
    private String subject;
    private String body;

    public EmailData(String toEmail, String subject, String body) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }
}
