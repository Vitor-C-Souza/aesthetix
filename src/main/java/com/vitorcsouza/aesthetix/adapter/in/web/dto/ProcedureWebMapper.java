package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.Procedure;
import org.springframework.stereotype.Component;

@Component
public class ProcedureWebMapper {
    public Procedure toDomain(ProcedureRequestDTO dto) {
        if (dto == null) return null;

        return Procedure.builder()
                .name(dto.name())
                .description(dto.description())
                .durationInMinutes(dto.durationInMinutes())
                .salePrice(dto.salePrice())
                .requireEquipment(dto.requireEquipment() != null && dto.requireEquipment())
                .active(dto.active() == null || dto.active())
                .build();
    }

    public ProcedureResponseDTO toResponse(Procedure domain) {
        if (domain == null) return null;

        return new ProcedureResponseDTO(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getDurationInMinutes(),
                domain.getSalePrice(),
                domain.getRequireEquipment(),
                domain.getActive(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
