package com.example.BookService.external.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailMessage {
    private String recipient;
    private String subject;
    private String content;
}
