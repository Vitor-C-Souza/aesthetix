package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class AppointmentWebMapper {
    public Appointment toDomain(AppointmentRequestDTO dto) {
        if (dto == null) return null;

        return Appointment.builder()
                .patient(Patient.builder().id(dto.patientId()).build())
                .professional(Professional.builder().id(dto.professionalId()).build())
                .procedure(Procedure.builder().id(dto.procedureId()).build())
                .startTime(dto.startTime())
                .endTime(dto.endTime())
                .status(dto.status() != null ? dto.status() : AppointmentStatus.SCHEDULED)
                .totalValue(dto.totalValue())
                .notes(dto.notes())
                .build();
    }

    public AppointmentResponseDTO toResponse(Appointment domain) {
        if (domain == null) return null;

        return new AppointmentResponseDTO(
                domain.getId(),
                domain.getPatient() != null ? domain.getPatient().getId() : null,
                domain.getPatient() != null ? domain.getPatient().getName() : null,
                domain.getProfessional() != null ? domain.getProfessional().getId() : null,
                domain.getProfessional() != null ? domain.getProfessional().getName() : null,
                domain.getProcedure() != null ? domain.getProcedure().getId() : null,
                domain.getProcedure() != null ? domain.getProcedure().getName() : null,
                domain.getStartTime(),
                domain.getEndTime(),
                domain.getStatus(),
                domain.getTotalValue(),
                domain.getNotes(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
