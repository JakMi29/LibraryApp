package com.example.AvailabilityNotifyService.external.client;

import com.example.AvailabilityNotifyService.external.request.EmailMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "KAFKA-SERVICE/emailSender")
public interface Kafka {
    @PostMapping("/send")
    public void sendEmail(@RequestBody List<EmailMessage> emails);
}
