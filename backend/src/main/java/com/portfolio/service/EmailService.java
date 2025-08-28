package com.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${portfolio.email.to:admin@portfolio.com}")
    private String adminEmail;
    
    @Value("${portfolio.email.from:noreply@portfolio.com}")
    private String fromEmail;
    
    public void sendContactEmail(String name, String email, String subject, String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(adminEmail);
            mailMessage.setFrom(fromEmail);
            mailMessage.setSubject("Portfolio Contact: " + subject);
            mailMessage.setText(
                "New contact form submission:\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Subject: " + subject + "\n\n" +
                "Message:\n" + message
            );
            
            mailSender.send(mailMessage);
        } catch (Exception e) {
            // Log error but don't throw exception to avoid breaking the contact form
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}