package com.example.BookService.infrastructure.database.repository;

import com.example.BookService.business.dao.BookDAO;
import com.example.BookService.infrastructure.database.entity.BookEntity;
import com.example.BookService.infrastructure.database.repository.jpa.BookJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class BookRepository implements BookDAO {

    private final BookJpaRepository repository;

    @Override
    public Optional<BookEntity> findById(Integer bookId) {
        return repository.findById(bookId);
    }

    @Override
    public void save(BookEntity book) {

    }
}
