package com.example.BookService.infrastructure.database.repository.jpa;

import com.example.BookService.infrastructure.database.entity.LibraryBookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryBookJpaRepository extends JpaRepository<LibraryBookEntity, Integer> {
    Optional<LibraryBookEntity> findByNameAndPublicationDate(String name, Integer publicationYear);
    @Query("SELECT lb FROM LibraryBookEntity lb WHERE lb.quantity > 0 AND lb.category = :category")
    Page<LibraryBookEntity> findAvailableByCategory(String category,Pageable pageable);
    Page<LibraryBookEntity> findByCategory(String category,Pageable pageable);
    @Query("SELECT lb FROM LibraryBookEntity lb WHERE lb.quantity > 0")
    Page<LibraryBookEntity> findAvailable(Pageable pageable);
}
