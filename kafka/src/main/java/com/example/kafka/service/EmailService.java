package com.example.kafka.service;

import com.example.kafka.domain.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final KafkaTemplate<String, EmailMessage> kafkaTemplate;


    public void sendEmails(List<EmailMessage> emails) {
        emails.forEach(t -> kafkaTemplate.send("email_topic", t));
    }

    @KafkaListener(topics = "email_topic", groupId = "email-consumer-group", containerFactory = "factory")
    public void consumeEmailMessage(EmailMessage emailMessage) {
        sendEmail(emailMessage);
    }

    private void sendEmail(EmailMessage emailMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailMessage.getRecipient());
        mailMessage.setSubject(emailMessage.getSubject());
        mailMessage.setText(emailMessage.getContent());

        javaMailSender.send(mailMessage);
    }
}
