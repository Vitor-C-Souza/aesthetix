package com.vitorcsouza.aesthetix.adapter.out.persistence.mapper;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.AnamnesisEntity;
import com.vitorcsouza.aesthetix.domain.model.Anamnesis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnamnesisMapper {
    private final PatientMapper patientMapper;
    private final ProfessionalMapper professionalMapper;

    public Anamnesis toDomain(AnamnesisEntity entity) {
        if (entity == null) return null;

        return Anamnesis.builder()
                .id(entity.getId())
                .patient(patientMapper.toDomain(entity.getPatient()))
                .professional(professionalMapper.toDomain(entity.getProfessional()))
                .formData(entity.getFormData())
                .signatureUrl(entity.getSignatureUrl())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public AnamnesisEntity toEntity(Anamnesis domain) {
        if (domain == null) return null;

        return AnamnesisEntity.builder()
                .id(domain.getId())
                .patient(patientMapper.toEntity(domain.getPatient()))
                .professional(professionalMapper.toEntity(domain.getProfessional()))
                .formData(domain.getFormData())
                .signatureUrl(domain.getSignatureUrl())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
