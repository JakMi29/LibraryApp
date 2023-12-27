package com.example.RentalService.business.dao;

import com.example.RentalService.infrastructure.database.entity.RentalEntity;

import java.util.Optional;

public interface OrderDAO {
    void save(RentalEntity order);
    Optional<RentalEntity> findByBookIdAndEmail(Integer bookId, String email);
}
