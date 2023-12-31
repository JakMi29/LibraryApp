package com.example.PaymentService.business.dao;

import com.example.PaymentService.domain.PaymentInfoResponse;
import com.example.PaymentService.domain.TransactionType;
import com.example.PaymentService.infrastructure.database.entity.PaymentEntity;

public interface PaymentDAO {
    void savePayment(PaymentEntity paymentEntity);

    PaymentEntity getPayment(TransactionType transactionType, Integer referenceId);
}
