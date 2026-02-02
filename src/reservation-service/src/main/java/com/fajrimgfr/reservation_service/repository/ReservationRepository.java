package com.fajrimgfr.reservation_service.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fajrimgfr.reservation_service.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByFieldIdAndStartAtBetween(
            UUID fieldId,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Reservation> findByStartAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );
}
