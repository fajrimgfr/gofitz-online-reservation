package com.fajrimgfr.field_service.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "field")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Field {
    @Id
    @GeneratedValue
    private UUID id;

    private String fieldName;
    private int countBall;
    private int sizeFieldLong;
    private int sizeFieldWide;
    private double priceFieldOnWeekend;
    private double priceFieldOnWeekday;
    private String imageSrc;
}
