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
@Table(name = "rentals")
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer rentalId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "received_date")
    private OffsetDateTime receivedDate;

    @Column(name = "return_date")
    private OffsetDateTime returnDate;

    @Column(name = "email")
    private String email;
}
