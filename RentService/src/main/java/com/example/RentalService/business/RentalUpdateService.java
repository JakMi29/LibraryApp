package com.example.RentalService.business;

import com.example.RentalService.business.dao.RentalDAO;
import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalUpdateService {
    private final BigDecimal DAY_FEE = BigDecimal.valueOf(0.50);
    private final int FREE_RENTAL_PERIOD = 14;
    private final RentalDAO rentalDAO;

    @Transactional
    @Scheduled(cron = "0 18 18 * * ?")
    void updateRental() {
        List<RentalEntity> rentalsToUpdate = rentalDAO.findAllToUpdate();

        List<RentalEntity> rentalsToSave = rentalsToUpdate.stream().map((this::update)).toList();

        rentalDAO.saveAll(rentalsToSave);
    }

    private RentalEntity update(RentalEntity entity) {
        int rentalPeriod = entity.getRentalPeriod() + 1;
        entity.setRentalPeriod(rentalPeriod);
        entity.setFee(
                rentalPeriod > 14 ? BigDecimal.valueOf(rentalPeriod - FREE_RENTAL_PERIOD).multiply(DAY_FEE) : BigDecimal.ZERO
        );
        return entity;
    }

}
