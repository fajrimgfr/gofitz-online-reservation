package com.fajrimgfr.reservation_service.util;

import com.fajrimgfr.reservation_service.dto.ReservationRequest;
import com.fajrimgfr.reservation_service.dto.ReservationResponse;
import com.fajrimgfr.reservation_service.entity.Reservation;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class ValueMapper {
    public static ReservationResponse reservationToDTO(Reservation reservation) {
        ReservationResponse reservationResponseDTO = new ReservationResponse();
        reservationResponseDTO.setId(reservation.getId());
        reservationResponseDTO.setUserId(reservation.getUserId());
        reservationResponseDTO.setFieldId(reservation.getFieldId());
        reservationResponseDTO.setStartAt(reservation.getStartAt());
        reservationResponseDTO.setEndAt(reservation.getEndAt());
        reservationResponseDTO.setStatus(reservation.getStatus());
        reservationResponseDTO.setPrice(reservation.getPrice());

        return reservationResponseDTO;
    }

    public static Reservation DTOtoReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setUserId(reservationRequest.getUserId());
        reservation.setFieldId(reservationRequest.getFieldId());
        reservation.setStartAt(reservationRequest.getStartAt());
        reservation.setEndAt(reservationRequest.getEndAt());
        reservation.setStatus(reservationRequest.getStatus());
        reservation.setPrice(reservationRequest.getPrice());

        return reservation;
    }

    public static String jsonAsString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JacksonException e) {
            e.printStackTrace();
        }
        return null;
    }
}
