package com.fajrimgfr.reservation_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private UUID id;
    private UUID userId;
    private UUID fieldId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String status;
    private double price;
}
