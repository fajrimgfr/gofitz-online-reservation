package com.fajrimgfr.field_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fajrimgfr.field_service.entity.Field;


public interface FieldRepository extends JpaRepository<Field,UUID>{
}
