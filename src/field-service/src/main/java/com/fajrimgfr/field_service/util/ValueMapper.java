package com.fajrimgfr.field_service.util;

import com.fajrimgfr.field_service.dto.FieldRequestDTO;
import com.fajrimgfr.field_service.dto.FieldResponseDTO;
import com.fajrimgfr.field_service.entity.Field;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class ValueMapper {
    public static FieldResponseDTO fieldToDTO(Field field) {
        FieldResponseDTO fieldResponseDTO = new FieldResponseDTO();
        fieldResponseDTO.setId(field.getId());
        fieldResponseDTO.setFieldName(field.getFieldName());
        fieldResponseDTO.setCountBall(field.getCountBall());
        fieldResponseDTO.setSizeFieldLong(field.getSizeFieldLong());
        fieldResponseDTO.setSizeFieldWide(field.getSizeFieldWide());
        fieldResponseDTO.setPriceFieldOnWeekend(field.getPriceFieldOnWeekend());
        fieldResponseDTO.setPriceFieldOnWeekday(field.getPriceFieldOnWeekday());
        fieldResponseDTO.setImageUrl(field.getImageUrl());

        return fieldResponseDTO;
    }

    public static Field DTOToField(FieldRequestDTO fieldRequestDTO) {
        Field field = new Field();
        field.setFieldName(fieldRequestDTO.getFieldName());
        field.setCountBall(fieldRequestDTO.getCountBall());
        field.setSizeFieldLong(fieldRequestDTO.getSizeFieldLong());
        field.setSizeFieldWide(fieldRequestDTO.getSizeFieldWide());
        field.setPriceFieldOnWeekend(fieldRequestDTO.getPriceFieldOnWeekend());
        field.setPriceFieldOnWeekday(fieldRequestDTO.getPriceFieldOnWeekday());
        field.setImageUrl(fieldRequestDTO.getImageUrl());

        return field;
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
