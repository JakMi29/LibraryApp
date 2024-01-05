package com.example.kafka.controller;

import com.example.kafka.domain.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emailSender")
@RequiredArgsConstructor
public class EmailController {
    private final KafkaTemplate<String, EmailMessage> kafkaTemplate;

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailMessage emailMessage) {
        kafkaTemplate.send("email_topic", emailMessage);
    }
}