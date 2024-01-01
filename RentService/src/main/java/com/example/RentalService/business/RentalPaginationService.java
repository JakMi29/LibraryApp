package com.example.RentalService.business;

import com.example.RentalService.business.dao.RentalDAO;
import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RentalPaginationService {
private final RentalDAO rentalDAO;

    public List<RentalEntity> getActiveRentals(String email, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("rentalId");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return rentalDAO.findActiveRentals(email, pageable);
    }
    public List<RentalEntity> getFinishedRentals(String email, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("rentalId");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return rentalDAO.findFinishedRentals(email, pageable);
    }
}
