package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.Anamnesis;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.model.Professional;
import org.springframework.stereotype.Component;

@Component
public class AnamnesisWebMapper {
    public Anamnesis toDomain(AnamnesisRequestDTO dto) {
        if (dto == null) return null;

        return Anamnesis.builder()
                .patient(Patient.builder().id(dto.patientId()).build())
                .professional(Professional.builder().id(dto.professionalId()).build())
                .formData(dto.formData())
                .signatureUrl(dto.signatureUrl())
                .build();
    }

    public AnamnesisResponseDTO toResponse(Anamnesis domain) {
        if (domain == null) return null;

        return new AnamnesisResponseDTO(
                domain.getId(),
                domain.getPatient() != null ? domain.getPatient().getId() : null,
                domain.getPatient() != null ? domain.getPatient().getName() : null,
                domain.getProfessional() != null ? domain.getProfessional().getId() : null,
                domain.getProfessional() != null ? domain.getProfessional().getName() : null,
                domain.getFormData(),
                domain.getSignatureUrl(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
