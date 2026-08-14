package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.Appointment;
import com.vitorcsouza.aesthetix.domain.model.EvolutionPhoto;
import com.vitorcsouza.aesthetix.domain.model.Patient;

public class EvolutionPhotoWebMapper {
    public EvolutionPhoto toDomain(EvolutionPhotoRequestDTO dto) {
        if (dto == null) return null;

        return EvolutionPhoto.builder()
                .patient(Patient.builder().id(dto.patientId()).build())
                .appointment(dto.appointmentId() != null ? Appointment.builder().id(dto.appointmentId()).build() : null)
                .photoUrl(dto.photoUrl())
                .photoType(dto.photoType())
                .notes(dto.notes())
                .build();
    }

    public EvolutionPhotoResponseDTO toResponse(EvolutionPhoto domain) {
        if (domain == null) return null;

        return new EvolutionPhotoResponseDTO(
                domain.getId(),
                domain.getPatient() != null ? domain.getPatient().getId() : null,
                domain.getPatient() != null ? domain.getPatient().getName() : null,
                domain.getAppointment() != null ? domain.getAppointment().getId() : null,
                domain.getPhotoUrl(),
                domain.getPhotoType(),
                domain.getNotes(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
