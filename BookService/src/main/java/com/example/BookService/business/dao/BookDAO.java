package com.example.BookService.business.dao;

import com.example.BookService.infrastructure.database.entity.BookEntity;

import java.util.Optional;

public interface BookDAO {
    Optional<BookEntity> findById(Integer bookId);

    void save(BookEntity book);
}
