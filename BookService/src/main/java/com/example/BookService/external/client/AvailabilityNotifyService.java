package com.example.BookService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AVAILABILITY-NOTIFY-SERVICE/follow)")
public interface AvailabilityNotifyService {
    @PostMapping("/send")
    void sendNotifications(@RequestBody String bookName, @RequestBody Integer bookId);

}
