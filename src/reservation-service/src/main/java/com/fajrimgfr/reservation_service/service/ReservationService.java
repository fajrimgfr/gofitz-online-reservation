package com.fajrimgfr.reservation_service.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fajrimgfr.reservation_service.dto.ReservationRequest;
import com.fajrimgfr.reservation_service.dto.ReservationResponse;
import com.fajrimgfr.reservation_service.entity.Reservation;
import com.fajrimgfr.reservation_service.exception.ReservationNotFoundException;
import com.fajrimgfr.reservation_service.exception.ReservationServiceBusinessException;
import com.fajrimgfr.reservation_service.repository.ReservationRepository;
import com.fajrimgfr.reservation_service.util.ValueMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public List<ReservationResponse> getReservations() {
        List<ReservationResponse> reservationResponseDTO = null;
        log.info("ReservationService:getReservations execution started.");

        try {
            List<Reservation> reservations = reservationRepository.findAll();
            if (reservations.isEmpty()) {
                reservationResponseDTO = Collections.emptyList();
            } else {
                reservationResponseDTO = reservations.stream().map(ValueMapper::reservationToDTO).toList();
            }
            log.debug("ReservationService:getReservations retrieving field from database  {}", ValueMapper.jsonAsString(reservationResponseDTO));
        } catch (Exception e) {
            log.error("Exception occurred while retrieving reservations from database, Exception message {}", e.getMessage());
            throw new ReservationServiceBusinessException("Exception occurred while fetch all fields from Database");
        }

        log.info("ReservationService:getReservations execution ended.");
        return reservationResponseDTO;
    }

    public ReservationResponse createReservation(String idempotencyKey, ReservationRequest reservationRequest) {
        ReservationResponse response = null;
        log.info("ReservationService:createReservation execution started");

        try {
            log.debug("ReservationService:createReservation request parameter {}.", ValueMapper.jsonAsString(reservationRequest));
            ReservationResponse cached = (ReservationResponse) redisTemplate.opsForValue().get("idem" + idempotencyKey);
            if (cached != null) {
                log.debug("ReservationService:createReservation getting old response from redis on same idempotency-key with value {}", ValueMapper.jsonAsString(response));
                return cached;
            } else {
                Reservation reservation = ValueMapper.DTOtoReservation(reservationRequest);

                reservationRepository.save(reservation);
                response = ValueMapper.reservationToDTO(reservation);
                redisTemplate.opsForValue().set(idempotencyKey, response);
                log.debug("ReservationService:createReservation creating reservation to database  {}", ValueMapper.jsonAsString(response));
            }
        } catch (Exception e) {
            log.error("Exception occurred while creation reservations to database, Exception message {}", e.getMessage());
            throw new ReservationServiceBusinessException("Exception occurred while create reservation to Database");
        }

        log.info("ReservationService:createReservation execution ended.");
        return response;
    }

    public ReservationResponse getReservationById(UUID id) {
        ReservationResponse response = null;
        log.info("ReservationService:getReservationById execution started");
        
        try {
            log.debug("ReservationService:getReservationById request ID {}.", id.toString());
            Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found with ID " + id.toString()));
            response = ValueMapper.reservationToDTO(reservation);

            log.debug("ReservationService:getReservationById retrieving reservation by id from database {}", ValueMapper.jsonAsString(response));
        } catch (Exception e) {
            log.error("Exception occurred while retrieving reservation by ID from database, Exception message {}", e.getMessage());
            throw new ReservationServiceBusinessException("Exception occurred while retrieve reservation from Database");
        }
        log.info("ReservationService:getReservationById execution ended.");
        return response;
    }

    public ReservationResponse updateReservationById(UUID id, ReservationRequest reservationRequest) {
        ReservationResponse response = null;

        try {
            Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found with ID " + id.toString()));
            reservation.setUserId(reservationRequest.getUserId());
            reservation.setFieldId(reservationRequest.getFieldId());
            reservation.setStartAt(reservationRequest.getStartAt());
            reservation.setEndAt(reservationRequest.getEndAt());
            reservation.setPrice(reservationRequest.getPrice());
            reservation.setStatus(reservationRequest.getStatus());

            reservationRepository.save(reservation);
            response = ValueMapper.reservationToDTO(reservation);

            log.debug("ReservationService:updateReservationById updating reservation by id to database {}", ValueMapper.jsonAsString(response));
        } catch (Exception e) {
            log.error("Exception occurred while updating reservation by ID from database, Exception message {}", e.getMessage());
            throw new ReservationServiceBusinessException("Exception occurred while updating reservation from Database");
        }

        log.info("ReservationService:updateReservationById execution ended.");
        return response;
    }

    public ReservationResponse deleteReservation(UUID id) {
        ReservationResponse response = null;
        log.info("ReservationService:deleteReservation execution started");

        try {
            log.debug("ReservationService:deleteReservation request ID {}.", id.toString());
            Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found with ID " + id.toString()));
            reservationRepository.delete(reservation);

            response = ValueMapper.reservationToDTO(reservation);

            log.debug("ReservationService:deleteReservation deleting reservation by id from database {}", ValueMapper.jsonAsString(response));
        } catch (Exception e) {
            log.error("Exception occurred while deleting reservation by ID from database, Exception message {}", e.getMessage());
            throw new ReservationServiceBusinessException("Exception occurred while delete reservation from Database");
        }

        log.info("ReservationService:deleteReservation execution ended.");
        return response;
    }



    public Map<UUID ,ReservationResponse> getAllReservationOnSpecificDate(UUID id) {
        Map<UUID ,ReservationResponse> responses = null;



        return responses;
    }
}
