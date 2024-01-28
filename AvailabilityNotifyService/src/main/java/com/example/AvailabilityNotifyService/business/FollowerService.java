package com.example.AvailabilityNotifyService.business;

import com.example.AvailabilityNotifyService.business.dao.FollowersDAO;
import com.example.AvailabilityNotifyService.domain.FollowBookRequest;
import com.example.AvailabilityNotifyService.infrastructure.database.entity.FollowersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowersDAO dao;

    @Transactional
    public void follow(FollowBookRequest request) {
        FollowersEntity entity = FollowersEntity.builder()
                .bookId(request.getBookId())
                .email(request.getEmail())
                .build();
        dao.save(entity);
    }

    @Transactional
    public void unFollow(FollowBookRequest request) {
        FollowersEntity entity = dao.findByBookIdAndEmail(request.getBookId(), request.getEmail()).orElseThrow();
        dao.remove(entity);
    }

    public void sendNotifications(Integer bookId) {
        List<FollowersEntity> followers = dao.findAllByBookId(bookId);

    }
}
