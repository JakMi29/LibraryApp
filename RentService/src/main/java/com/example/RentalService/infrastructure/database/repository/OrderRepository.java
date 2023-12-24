package com.example.RentalService.infrastructure.database.repository;

import com.example.RentalService.business.dao.OrderDAO;
import com.example.RentalService.infrastructure.database.entity.OrderEntity;
import com.example.RentalService.infrastructure.database.repository.jpa.OrderJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrderRepository implements OrderDAO {
    private final OrderJpaRepository repository;

    @Override
    public void save(OrderEntity order) {
        repository.save(order);
    }
}
