package com.example.RentalService.infrastructure.database.repository.jpa;

import com.example.RentalService.infrastructure.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity,Integer> {

}
