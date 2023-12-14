package com.example.RentalService.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.OffsetDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderId")
    private Integer orderId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "received_date")
    private OffsetDateTime receivedDate;

    @Column(name = "return_date")
    private OffsetDateTime returnDate;

}
