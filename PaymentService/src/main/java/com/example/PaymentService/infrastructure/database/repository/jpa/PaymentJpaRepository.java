package com.example.PaymentService.infrastructure.database.repository.jpa;

import com.example.PaymentService.domain.PaymentInfoResponse;
import com.example.PaymentService.domain.TransactionType;
import com.example.PaymentService.infrastructure.database.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Integer> {


    PaymentEntity findByTransactionTypeAndReferenceId(TransactionType paymentType, Integer referenceId);
}
