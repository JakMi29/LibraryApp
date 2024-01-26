package com.example.AvailabilityNotifyService.business.dao;

import com.example.AvailabilityNotifyService.infrastructure.database.entity.FollowersEntity;

import java.util.List;
import java.util.Optional;

public interface FollowersDAO {
    void save(FollowersEntity entity);

    void remove(FollowersEntity entity);

    List<FollowersEntity> findAllByBookId(Integer bookId);

    Optional<FollowersEntity> findByBookIdAndEmail(Integer bookId, String email);
}
