package com.example.BookService.infrastructure.database.repository.jpa;

import com.example.BookService.infrastructure.database.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity,Integer> {
    Optional<BookEntity> findByNameAndPublicationDate(String name, Integer publicationYear);
}
