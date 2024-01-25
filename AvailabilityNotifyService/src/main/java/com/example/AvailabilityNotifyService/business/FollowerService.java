package com.example.AvailabilityNotifyService.business;

import com.example.AvailabilityNotifyService.business.dao.FollowersDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowersDAO dao;

    public void follow(FollowerService service) {
    }

    public void unFollow(FollowerService service) {
    }
}
