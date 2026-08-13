package com.vitorcsouza.aesthetix.adapter.out.persistence.mapper;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.AppointmentEntity;
import com.vitorcsouza.aesthetix.domain.model.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {
    private final PatientMapper patientMapper;
    private final ProfessionalMapper professionalMapper;
    private final ProcedureMapper procedureMapper;

    public Appointment toDomain(AppointmentEntity entity) {
        if (entity == null) return null;

        return Appointment.builder()
                .id(entity.getId())
                .patient(patientMapper.toDomain(entity.getPatient()))
                .professional(professionalMapper.toDomain(entity.getProfessional()))
                .procedure(procedureMapper.toDomain(entity.getProcedure()))
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .status(entity.getStatus())
                .totalValue(entity.getTotalValue())
                .notes(entity.getNotes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public AppointmentEntity toEntity(Appointment domain) {
        if (domain == null) return null;

        return AppointmentEntity.builder()
                .id(domain.getId())
                .patient(patientMapper.toEntity(domain.getPatient()))
                .professional(professionalMapper.toEntity(domain.getProfessional()))
                .procedure(procedureMapper.toEntity(domain.getProcedure()))
                .startTime(domain.getStartTime())
                .endTime(domain.getEndTime())
                .status(domain.getStatus())
                .totalValue(domain.getTotalValue())
                .notes(domain.getNotes())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
