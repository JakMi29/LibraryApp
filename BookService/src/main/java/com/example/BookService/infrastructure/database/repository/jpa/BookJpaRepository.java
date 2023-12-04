package com.example.BookService.infrastructure.database.repository.jpa;

import com.example.BookService.infrastructure.database.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity,Integer> {
}
