package com.example.OpinionService.infrastructure.database.repository.jpa;

import com.example.OpinionService.infrastructure.database.entity.OpinionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpinionJpaRepository extends JpaRepository<OpinionEntity,Integer> {
    List<OpinionEntity> findAByBookId(Integer bookId, Pageable pageable);
}
