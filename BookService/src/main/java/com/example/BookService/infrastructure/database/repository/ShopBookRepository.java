package com.example.BookService.infrastructure.database.repository;

import com.example.BookService.business.dao.ShopBookDAO;
import com.example.BookService.infrastructure.database.entity.ShopBookEntity;
import com.example.BookService.infrastructure.database.repository.jpa.ShopBookJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ShopBookRepository implements ShopBookDAO {

    private final ShopBookJpaRepository repository;

    @Override
    public Optional<ShopBookEntity> findById(Integer bookId) {
        return repository.findById(bookId);
    }

    @Override
    public void save(ShopBookEntity book) {
        repository.save(book);
    }

    @Override
    public Optional<ShopBookEntity> findByNameAndPublicationDate(String name, Integer publicationYear) {
        return repository.findByNameAndPublicationDate(name, publicationYear);
    }

    @Override
    public List<ShopBookEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable).stream().toList();
    }

    @Override
    public List<ShopBookEntity> findAllByCategory(Pageable pageable, String category) {
        return null;
    }

    @Override
    public List<ShopBookEntity> findAvailable(Pageable pageable) {
        return null;
    }

    @Override
    public List<ShopBookEntity> findAvailableByCategory(Pageable pageable, String category) {
        return null;
    }
}
