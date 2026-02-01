package com.fajrimgfr.reservation_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fajrimgfr.reservation_service.entity.Reservation;

import jakarta.persistence.LockModeType;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
