package com.example.RentalService.infrastructure.database.repository;

import com.example.RentalService.business.dao.RentalDAO;
import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import com.example.RentalService.infrastructure.database.repository.jpa.RentalJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RentalRepository implements RentalDAO {
    private final RentalJpaRepository repository;

    @Override
    public void save(RentalEntity order) {
        repository.save(order);
    }

    @Override
    public void saveAll(List<RentalEntity> rentals) {
        repository.saveAll(rentals);
    }

    @Override
    public List<RentalEntity> findAllToUpdate() {
        return repository.findAllByReturnDateIsNull();
    }

    @Override
    public Optional<RentalEntity> findActiveByBookIdAndEmail(Integer bookId, String email) {
        return repository.findByReturnDateIsNullAndBookIdAndEmail(bookId, email);
    }

    @Override
    public List<RentalEntity> findActiveRentals(String email,Pageable pageable) {
        return repository.findByEmailAndReturnDateIsNull(email,pageable);
    }

    @Override
    public List<RentalEntity> findFinishedRentals(String email, Pageable pageable) {
        return repository.findByEmailAndReturnDateIsNotNull(email,pageable);
    }
}
