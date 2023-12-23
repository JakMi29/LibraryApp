package com.example.BookService.business.dao;

import com.example.BookService.infrastructure.database.entity.BookEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    Optional<BookEntity> findById(Integer bookId);

    void save(BookEntity book);

    Optional<BookEntity> findByNameAndPublicationDate(String name, Integer publicationYear);

    List<BookEntity> findAll(Pageable pageable);
}
