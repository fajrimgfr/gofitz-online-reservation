package com.fajrimgfr.reservation_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    @NotBlank(message = "User's ID shouldn't be NULL or EMPTY")
    private UUID userId;
    @NotBlank(message = "Field's ID shouldn't be NULL or EMPTY")
    private UUID fieldId;
    @NotBlank(message = "Start at time shouldn't be NULL or EMPTY")
    private LocalDateTime startAt;
    @NotBlank(message = "End at time shouldn't be NULL or EMPTY")
    private LocalDateTime endAt;
    @NotBlank(message = "Status shouldn't be NULL or EMPTY")
    private String status;
    @NotBlank(message = "Price shouldn't be NULL or EMPTY")
    private double price;
}
