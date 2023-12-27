package com.example.BookService.business.dao;

import com.example.BookService.infrastructure.database.entity.ShopBookEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ShopBookDAO {
    Optional<ShopBookEntity> findById(Integer bookId);

    void save(ShopBookEntity book);

    Optional<ShopBookEntity> findByNameAndPublicationDate(String name, Integer publicationYear);

    List<ShopBookEntity> findAll(Pageable pageable);

    List<ShopBookEntity> findAllByCategory(Pageable pageable, String category);

    List<ShopBookEntity> findAvailable(Pageable pageable);

    List<ShopBookEntity> findAvailableByCategory(Pageable pageable, String category);
}
