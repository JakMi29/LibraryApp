package com.example.AvailabilityNotifyService.infrastructure.database.repository;

import com.example.AvailabilityNotifyService.business.dao.FollowersDAO;
import com.example.AvailabilityNotifyService.infrastructure.database.entity.FollowersEntity;
import com.example.AvailabilityNotifyService.infrastructure.database.repository.jpa.FollowersJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FollowersRepository implements FollowersDAO {
    private final FollowersJpaRepository repository;

    @Override
    public void save(FollowersEntity entity) {
        repository.save(entity);
    }

    @Override
    public void remove(FollowersEntity entity) {
        repository.delete(entity);
    }

    @Override
    public List<FollowersEntity> findAllByBookId(Integer bookId) {
        return repository.findAllByBookId(bookId);
    }

    @Override
    public Optional<FollowersEntity> findByBookIdAndEmail(Integer bookId, String email) {
        return repository.findByBookIdAndEmail(bookId,email);
    }
}
