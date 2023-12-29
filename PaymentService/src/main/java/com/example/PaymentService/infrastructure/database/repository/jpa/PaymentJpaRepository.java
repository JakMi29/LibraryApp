package com.example.PaymentService.infrastructure.database.repository.jpa;

import com.example.PaymentService.infrastructure.database.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Integer> {


}
