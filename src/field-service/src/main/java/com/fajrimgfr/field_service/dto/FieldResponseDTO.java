package com.fajrimgfr.field_service.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldResponseDTO {
    private UUID id;
    private String fieldName;
    private int countBall;
    private int sizeFieldLong;
    private int sizeFieldWide;
    private double priceFieldOnWeekend;
    private double priceFieldOnWeekday;
    private String imageUrl;
}
