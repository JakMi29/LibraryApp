package com.example.BookService.infrastructure.database.repository;

import com.example.BookService.business.dao.BookDAO;
import com.example.BookService.infrastructure.database.entity.LibraryBookEntity;
import com.example.BookService.infrastructure.database.repository.jpa.LibraryBookJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class LibraryBookRepository implements BookDAO {

    private final LibraryBookJpaRepository repository;

    @Override
    public Optional<LibraryBookEntity> findById(Integer bookId) {
        return repository.findById(bookId);
    }

    @Override
    public void save(LibraryBookEntity book) {
        repository.save(book);
    }

    @Override
    public Optional<LibraryBookEntity> findByNameAndPublicationDate(String name, Integer publicationYear) {
        return repository.findByNameAndPublicationDate(name, publicationYear);
    }

    @Override
    public List<LibraryBookEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable).stream().toList();
    }
}
