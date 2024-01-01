package com.example.RentalService.business.dao;

import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RentalDAO {
    void save(RentalEntity order);
    void saveAll(List<RentalEntity> rentals);
    List<RentalEntity> findAllToUpdate();
    Optional<RentalEntity> findActiveByBookIdAndEmail(Integer bookId, String email);

    List<RentalEntity> findActiveRentals(String email,Pageable pageable);

    List<RentalEntity> findFinishedRentals(String email, Pageable pageable);
}
