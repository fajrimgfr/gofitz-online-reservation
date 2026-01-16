package com.fajrimgfr.field_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldRequestDTO {
    @NotBlank(message = "Field's name shouldn't be NULL or EMPTY")
    private String fieldName;

    @NotBlank(message = "Count's ball shouldn't be NULL or EMPTY")
    private int countBall;

    @NotBlank(message = "Size field's long shouldn't be NULL or EMPTY")
    private int sizeFieldLong;

    @NotBlank(message = "Size field's wide shouldn't be NULL or EMPTY")
    private int sizeFieldWide;

    @NotBlank(message = "Price field's on weekend shouldn't be NULL or EMPTY")
    private double priceFieldOnWeekend;

    @NotBlank(message = "Price field's on weekday shouldn't be NULL or EMPTY")
    private double priceFieldOnWeekday;

    @NotBlank(message = "Image url's shouldn't be NULL or EMPTY")
    private String imageUrl;
}
