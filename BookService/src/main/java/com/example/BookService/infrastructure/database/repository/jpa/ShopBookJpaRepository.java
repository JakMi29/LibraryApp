package com.example.BookService.infrastructure.database.repository.jpa;

import com.example.BookService.infrastructure.database.entity.ShopBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopBookJpaRepository extends JpaRepository<ShopBookEntity, Integer> {
    Optional<ShopBookEntity> findByNameAndPublicationDate(String name, Integer publicationYear);
}
