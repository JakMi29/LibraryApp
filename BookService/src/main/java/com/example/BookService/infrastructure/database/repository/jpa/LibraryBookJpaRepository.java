package com.example.BookService.infrastructure.database.repository.jpa;

import com.example.BookService.infrastructure.database.entity.LibraryBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryBookJpaRepository extends JpaRepository<LibraryBookEntity,Integer> {
    Optional<LibraryBookEntity> findByNameAndPublicationDate(String name, Integer publicationYear);
}
