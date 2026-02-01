package com.fajrimgfr.reservation_service.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId;
    private UUID fieldId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String status;
    private double price;
}
