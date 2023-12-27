package com.example.RentalService.infrastructure.database.repository;

import com.example.RentalService.business.dao.OrderDAO;
import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import com.example.RentalService.infrastructure.database.repository.jpa.RentalJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RentalRepository implements OrderDAO {
    private final RentalJpaRepository repository;

    @Override
    public void save(RentalEntity order) {
        repository.save(order);
    }

    @Override
    public Optional<RentalEntity> findByBookIdAndEmail(Integer bookId, String email) {
        return repository.findByBookIdAndEmail(bookId, email);
    }
}
