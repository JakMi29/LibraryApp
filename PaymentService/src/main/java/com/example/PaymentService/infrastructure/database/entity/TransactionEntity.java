package com.example.PaymentService.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Entity
@Table(name="transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "mode")
    private String paymentMode;

    @Column(name = "payment_date")
    private Instant paymentDate;

    @Column(name = "amount")
    private long amount;
}
