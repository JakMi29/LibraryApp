package com.example.kafka.domain;

import lombok.Data;

@Data
public class EmailMessage {
    private String recipient;
    private String subject;
    private String content;
}
