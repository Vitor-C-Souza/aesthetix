package com.vitorcsouza.aesthetix.adapter.out.persistence.mapper;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.EvolutionPhotoEntity;
import com.vitorcsouza.aesthetix.domain.model.EvolutionPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvolutionPhotoMapper {
    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;

    public EvolutionPhoto toDomain(EvolutionPhotoEntity entity) {
        if (entity == null) return null;

        return EvolutionPhoto.builder()
                .id(entity.getId())
                .patient(patientMapper.toDomain(entity.getPatient()))
                .appointment(appointmentMapper.toDomain(entity.getAppointment()))
                .photoUrl(entity.getPhotoUrl())
                .photoType(entity.getPhotoType())
                .notes(entity.getNotes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public EvolutionPhotoEntity toEntity(EvolutionPhoto domain) {
        if (domain == null) return null;

        return EvolutionPhotoEntity.builder()
                .id(domain.getId())
                .patient(patientMapper.toEntity(domain.getPatient()))
                .appointment(appointmentMapper.toEntity(domain.getAppointment()))
                .photoUrl(domain.getPhotoUrl())
                .photoType(domain.getPhotoType())
                .notes(domain.getNotes())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
