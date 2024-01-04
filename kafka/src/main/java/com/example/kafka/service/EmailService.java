package com.example.kafka.service;

import com.example.kafka.domain.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "email_topic", groupId = "email-consumer-group",containerFactory = "factory")
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
