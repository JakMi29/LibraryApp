package com.example.PaymentService.business.dao;

import com.example.PaymentService.infrastructure.database.entity.PaymentEntity;

public interface PaymentDAO {
    void savePayment(PaymentEntity paymentEntity);

    PaymentEntity getPayment(Integer referenceId);
}
