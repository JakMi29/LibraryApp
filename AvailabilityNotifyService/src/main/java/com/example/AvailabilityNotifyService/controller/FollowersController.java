package com.example.AvailabilityNotifyService.controller;

import com.example.AvailabilityNotifyService.business.FollowerService;
import com.example.AvailabilityNotifyService.domain.FollowBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/follow")
@RequiredArgsConstructor
public class FollowersController {
    private final FollowerService service;

    @PostMapping()
    public void followBook(@RequestBody FollowBookRequest request) {
        service.follow(service);
    }
    @DeleteMapping()
    public void unFollowBook(@RequestBody FollowBookRequest request) {
        service.unFollow(service);
    }
}
