package com.fajrimgfr.field_service.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fajrimgfr.field_service.dto.FieldRequestDTO;
import com.fajrimgfr.field_service.dto.FieldResponseDTO;
import com.fajrimgfr.field_service.entity.Field;
import com.fajrimgfr.field_service.exception.FieldServiceBusinessException;
import com.fajrimgfr.field_service.repository.FieldRepository;
import com.fajrimgfr.field_service.util.ValueMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FieldService {
    private final FieldRepository fieldRepository;

    public List<FieldResponseDTO> getFields() {
        List<FieldResponseDTO> fieldResponseDTOs = null;
        log.info("FieldService:getFields execution started.");

        try {
            List<Field> fields = fieldRepository.findAll();
            if (fields.isEmpty()) {
                fieldResponseDTOs = Collections.emptyList();
            } else {
                fieldResponseDTOs = fields.stream().map(ValueMapper::fieldToDTO).toList();
            }
            log.debug("FieldService:getFields retrieving field from database  {}", ValueMapper.jsonAsString(fieldResponseDTOs));
        } catch (Exception e) {
            log.error("Exception occurred while retrieving fields from database, Exception message {}", e.getMessage());
            throw new FieldServiceBusinessException("Exception occurred while fetch all fields from Database");
        }

        log.info("FieldService:getFields execution ended.");
        return fieldResponseDTOs;
    }

    public FieldResponseDTO createField(FieldRequestDTO fieldRequestDTO) {
        FieldResponseDTO fieldResponseDTO = null;
        log.info("FieldService:createField execution started.");

        try {
            log.debug("FieldService:createField request parameter {}", ValueMapper.jsonAsString(fieldRequestDTO));
            Field field = ValueMapper.DTOToField(fieldRequestDTO);

            fieldRepository.save(field);
            fieldResponseDTO = ValueMapper.fieldToDTO(field);
            log.debug("FieldService:createField creating field to database {}", ValueMapper.jsonAsString(field));
        } catch (Exception e) {
            log.error("Exception occurred while creating fields into database, Exception message {}", e.getMessage());
            throw new FieldServiceBusinessException("Exception occured while creating field to Database");
        }

        return fieldResponseDTO;
    }

    public FieldResponseDTO updateField(FieldRequestDTO fieldRequestDTO) {
        FieldResponseDTO fieldResponseDTO = null;
        
        return fieldResponseDTO;
    }
}
