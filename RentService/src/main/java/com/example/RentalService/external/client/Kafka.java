package com.example.RentalService.external.client;

import com.example.RentalService.external.request.EmailMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="KAFKA-SERVICE/emailSender")
public interface Kafka {
    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailMessage emailMessage);

}
