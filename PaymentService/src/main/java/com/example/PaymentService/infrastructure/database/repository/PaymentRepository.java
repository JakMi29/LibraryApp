package com.example.PaymentService.infrastructure.database.repository;

import com.example.PaymentService.business.dao.PaymentDAO;
import com.example.PaymentService.domain.PaymentInfoResponse;
import com.example.PaymentService.domain.TransactionType;
import com.example.PaymentService.infrastructure.database.entity.PaymentEntity;
import com.example.PaymentService.infrastructure.database.repository.jpa.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepository implements PaymentDAO {

    private final PaymentJpaRepository repository;

    @Override
    public void savePayment(PaymentEntity paymentEntity) {
        repository.save(paymentEntity);
    }

    @Override
    public PaymentEntity getPayment(TransactionType transactionType, Integer referenceId) {
        return repository.findByTransactionTypeAndReferenceId(transactionType,referenceId);
    }
}
