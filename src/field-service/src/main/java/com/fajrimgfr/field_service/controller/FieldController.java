package com.fajrimgfr.field_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fajrimgfr.field_service.dto.APIResponse;
import com.fajrimgfr.field_service.dto.FieldRequestDTO;
import com.fajrimgfr.field_service.dto.FieldResponseDTO;
import com.fajrimgfr.field_service.service.FieldService;
import com.fajrimgfr.field_service.util.ValueMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/field")
@AllArgsConstructor
@Slf4j
public class FieldController {
    public static final String SUCCESS = "Success";
    private FieldService fieldService;

    @GetMapping
    public ResponseEntity<APIResponse> getFields() {
        List<FieldResponseDTO> fields = fieldService.getFields();
        APIResponse<List<FieldResponseDTO>> responseDTO = APIResponse
                .<List<FieldResponseDTO>>builder()
                .status(SUCCESS)
                .results(fields)
                .build();

        log.info("FieldController::getFields response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createField(@RequestBody @Valid FieldRequestDTO fieldRequestDTO) {
        log.info("FieldController::createField request body {}", ValueMapper.jsonAsString(fieldRequestDTO));

        FieldResponseDTO fieldResponseDTO = fieldService.createField(fieldRequestDTO);

        APIResponse<FieldResponseDTO> responseDTO = APIResponse
                .<FieldResponseDTO>builder()
                .status(SUCCESS)
                .results(fieldResponseDTO)
                .build();

        log.info("FieldController::createField response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{fieldID}")
    public ResponseEntity<APIResponse> getFieldByID(@PathVariable UUID fieldID) {
        log.info("FieldController::getField by id {}", fieldID.toString());

        FieldResponseDTO fieldResponseDTO = fieldService.getFieldByID(fieldID);

        APIResponse<FieldResponseDTO> responseDTO = APIResponse
                .<FieldResponseDTO>builder()
                .status(SUCCESS)
                .results(fieldResponseDTO)
                .build();

        log.info("FieldController::getFieldByID response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("{fieldID}")
    public ResponseEntity<APIResponse> updateField(@PathVariable UUID fieldID, @RequestParam @Valid FieldRequestDTO fieldRequestDTO) {
        log.info("FieldController::updateField by id {} and request param {}", fieldID.toString(), ValueMapper.jsonAsString(fieldRequestDTO));

        FieldResponseDTO fieldResponseDTO = fieldService.updateField(fieldID, fieldRequestDTO);

        APIResponse<FieldResponseDTO> responseDTO = APIResponse
                .<FieldResponseDTO>builder()
                .status(SUCCESS)
                .results(fieldResponseDTO)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
