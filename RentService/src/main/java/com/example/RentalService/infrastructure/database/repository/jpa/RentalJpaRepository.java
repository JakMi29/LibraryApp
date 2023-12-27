package com.example.RentalService.infrastructure.database.repository.jpa;

import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalJpaRepository extends JpaRepository<RentalEntity,Integer> {

    Optional<RentalEntity> findByBookIdAndEmail(Integer bookId, String email);
}
