package com.example.RentalService.business.dao;

import com.example.RentalService.infrastructure.database.entity.OrderEntity;

public interface OrderDAO {
    void save(OrderEntity order);
}
