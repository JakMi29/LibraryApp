package com.example.PaymentService.infrastructure.database.entity;

import com.example.PaymentService.domain.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "reference_id")
    private Integer referenceId;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Column(name = "payment_date")
    private OffsetDateTime paymentDate;

    @Column(name = "amount")
    private BigDecimal amount;
}


