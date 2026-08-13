package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.Professional;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalWebMapper {
    public Professional toDomain(ProfessionalRequestDTO dto) {
        if (dto == null) return null;

        return Professional.builder()
                .name(dto.name())
                .cpf(dto.cpf())
                .phone(dto.phone())
                .specialty(dto.specialty())
                .commissionRate(dto.commissionRate())
                .colorCode(dto.colorCode())
                .active(dto.active() == null || dto.active())
                .build();
    }

    public ProfessionalResponseDTO toResponse(Professional domain) {
        if (domain == null) return null;

        return new ProfessionalResponseDTO(
                domain.getId(),
                domain.getName(),
                domain.getCpf(),
                domain.getPhone(),
                domain.getSpecialty(),
                domain.getCommissionRate(),
                domain.getColorCode(),
                domain.getActive(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
