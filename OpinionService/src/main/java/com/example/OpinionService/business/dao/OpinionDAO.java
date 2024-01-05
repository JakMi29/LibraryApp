package com.example.OpinionService.business.dao;

import com.example.OpinionService.infrastructure.database.entity.OpinionEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OpinionDAO {

    void save(OpinionEntity Opinion);


    List<OpinionEntity> findAll(Pageable pageable);
    List<OpinionEntity> findByBook(Integer bookId, Pageable pageable);
}
