package com.example.PaymentService.business;

import com.example.PaymentService.business.dao.PaymentDAO;
import com.example.PaymentService.domain.PaymentRequest;
import com.example.PaymentService.infrastructure.database.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentDAO paymentDAO;
    public void doPayment(PaymentRequest paymentRequest) {
        PaymentEntity payment=PaymentEntity.builder()
                .paymentMode(paymentRequest.getPaymentMode())
                .amount(paymentRequest.getAmount())
                .orderId(paymentRequest.getOrderId())
                .rentalId(paymentRequest.getRentalId())
                .email(paymentRequest.getEmail())
                .build();
        paymentDAO.savePayment(payment);
    }
}
