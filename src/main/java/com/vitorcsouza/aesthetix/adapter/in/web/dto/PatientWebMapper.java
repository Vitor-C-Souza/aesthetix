package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientWebMapper {
    public Patient toDomain(PatientRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return Patient.builder()
                .name(dto.name())
                .cpf(dto.cpf())
                .phone(dto.phone())
                .email(dto.email())
                .birthDate(dto.birthDate())
                .notes(dto.notes())
                .build();
    }

    public PatientResponseDTO toResponse(Patient domain) {
        if (domain == null) {
            return null;
        }
        return new PatientResponseDTO(
                domain.getId(),
                domain.getName(),
                domain.getCpf(),
                domain.getPhone(),
                domain.getEmail(),
                domain.getBirthDate(),
                domain.getNotes(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
