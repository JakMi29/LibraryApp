package com.example.RentalService.infrastructure.database.repository.jpa;

import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalJpaRepository extends JpaRepository<RentalEntity,Integer> {

    Optional<RentalEntity> findByReturnDateIsNullAndBookIdAndEmail(Integer bookId, String email);

    List<RentalEntity> findAllByReturnDateIsNull();

    List<RentalEntity> findByEmailAndReturnDateIsNull(String email, Pageable pageable);

    List<RentalEntity> findByEmailAndReturnDateIsNotNull(String email, Pageable pageable);
}
