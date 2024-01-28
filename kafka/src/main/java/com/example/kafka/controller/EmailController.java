package com.example.kafka.controller;

import com.example.kafka.domain.EmailMessage;
import com.example.kafka.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emailSender")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService service;

    @PostMapping("/send")
    public void sendEmail(@RequestBody List<EmailMessage> emails) {
        service.sendEmails(emails);
    }
}