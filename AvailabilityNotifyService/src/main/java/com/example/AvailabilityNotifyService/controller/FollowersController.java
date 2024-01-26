package com.example.AvailabilityNotifyService.controller;

import com.example.AvailabilityNotifyService.business.FollowerService;
import com.example.AvailabilityNotifyService.domain.FollowBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowersController {
    private final FollowerService service;

    @PostMapping
    public void followBook(@RequestBody FollowBookRequest request) {
        service.follow(request);
    }

    @DeleteMapping
    public void unFollowBook(@RequestBody FollowBookRequest request) {
        service.unFollow(request);
    }

    @PostMapping("/send/{bookId}")
    public void sendNotifications(@RequestBody Integer bookId) {
        service.sendNotifications(bookId);
    }
}
