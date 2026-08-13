package com.vitorcsouza.aesthetix.adapter.out.persistence.mapper;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.PatientEntity;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public Patient toDomain(PatientEntity entity) {
        if (entity == null) return null;

        return Patient.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .birthDate(entity.getBirthDate())
                .notes(entity.getNotes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public PatientEntity toEntity(Patient domain) {
        if (domain == null) return null;

        return PatientEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .cpf(domain.getCpf())
                .phone(domain.getPhone())
                .email(domain.getEmail())
                .birthDate(domain.getBirthDate())
                .notes(domain.getNotes())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
