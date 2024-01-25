package com.example.AvailabilityNotifyService.infrastructure.database.repository;

import com.example.AvailabilityNotifyService.business.dao.FollowersDAO;
import com.example.AvailabilityNotifyService.infrastructure.database.repository.jpa.FollowersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowersRepository implements FollowersDAO {
    private final FollowersJpaRepository repository;
}
