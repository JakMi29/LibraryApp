package com.example.PaymentService.business;

import com.example.PaymentService.business.dao.PaymentDAO;
import com.example.PaymentService.domain.*;
import com.example.PaymentService.infrastructure.database.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentDAO paymentDAO;
    @Transactional
    public void doPayment(PaymentRequest paymentRequest) {
        PaymentEntity payment=PaymentEntity.builder()
                .paymentMode(PaymentMode.valueOf(paymentRequest.getPaymentMode()))
                .transactionType(TransactionType.valueOf(paymentRequest.getTransactionType()))
                .amount(paymentRequest.getAmount())
                .paymentDate(OffsetDateTime.now())
                .referenceId(paymentRequest.getReferenceId())
                .email(paymentRequest.getEmail())
                .build();
        paymentDAO.savePayment(payment);
    }
@Transactional
    public PaymentInfoResponse getPaymentInfo(PaymentInfoRequest request) {
        PaymentEntity payment=paymentDAO.getPayment(
                TransactionType.valueOf(request.getTransactionType())
                ,request.getReferenceId()
        );
        PaymentInfoResponse payments= PaymentInfoResponse.builder()
                .paymentMode(payment.getPaymentMode().toString())
                .amount(payment.getAmount().toString())
                .time(payment.getPaymentDate())
                .build();
        return payments;
    }
}
