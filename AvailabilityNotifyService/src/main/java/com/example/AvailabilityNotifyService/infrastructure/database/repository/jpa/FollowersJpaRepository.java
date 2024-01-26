package com.example.AvailabilityNotifyService.infrastructure.database.repository.jpa;

import com.example.AvailabilityNotifyService.infrastructure.database.entity.FollowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowersJpaRepository extends JpaRepository<FollowersEntity,Integer> {

    List<FollowersEntity> findAllByBookId(Integer bookId);

    Optional<FollowersEntity> findByBookIdAndEmail(Integer bookId, String email);
}
