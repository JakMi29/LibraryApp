package com.example.BookService.infrastructure.database.repository;

import com.example.BookService.business.dao.BookDAO;
import com.example.BookService.infrastructure.database.entity.BookEntity;
import com.example.BookService.infrastructure.database.repository.jpa.BookJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        repository.save(book);
    }

    @Override
    public Optional<BookEntity> findByNameAndPublicationDate(String name, Integer publicationYear) {
        return repository.findByNameAndPublicationDate(name, publicationYear);
    }

    @Override
    public List<BookEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable).stream().toList();
    }
}
