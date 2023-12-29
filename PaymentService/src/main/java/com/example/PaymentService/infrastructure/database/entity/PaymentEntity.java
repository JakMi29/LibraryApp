package com.example.PaymentService.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "rental_id")
    private Integer rentalId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "mode")
    private String paymentMode;

    @Column(name = "payment_date")
    private OffsetDateTime paymentDate;

    @Column(name = "amount")
    private BigDecimal amount;
}
