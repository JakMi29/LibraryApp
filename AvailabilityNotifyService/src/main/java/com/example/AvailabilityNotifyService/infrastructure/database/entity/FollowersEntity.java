package com.example.AvailabilityNotifyService.infrastructure.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CollectionId;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name="followers")
public class FollowersEntity {

    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="bookId")
    private Integer bookId;
    @Column(name="email")
    private String email;

}
