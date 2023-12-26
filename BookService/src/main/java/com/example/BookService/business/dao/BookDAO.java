package com.example.BookService.business.dao;

import com.example.BookService.infrastructure.database.entity.LibraryBookEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    Optional<LibraryBookEntity> findById(Integer bookId);

    void save(LibraryBookEntity book);

    Optional<LibraryBookEntity> findByNameAndPublicationDate(String name, Integer publicationYear);

    List<LibraryBookEntity> findAll(Pageable pageable);
}
