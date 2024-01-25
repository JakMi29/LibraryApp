package com.example.AvailabilityNotifyService.infrastructure.database.repository.jpa;

import com.example.AvailabilityNotifyService.infrastructure.database.entity.FollowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowersJpaRepository extends JpaRepository<FollowersEntity,Integer> {

}
