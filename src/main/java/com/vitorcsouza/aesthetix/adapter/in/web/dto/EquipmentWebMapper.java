package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.Equipment;
import org.springframework.stereotype.Component;

@Component
public class EquipmentWebMapper {
    public Equipment toDomain(EquipmentRequestDTO dto) {
        if (dto == null) return null;

        return Equipment.builder()
                .name(dto.name())
                .serialNumber(dto.serialNumber())
                .active(dto.active() == null || dto.active())
                .build();
    }

    public EquipmentResponseDTO toResponse(Equipment domain) {
        if (domain == null) return null;

        return new EquipmentResponseDTO(
                domain.getId(),
                domain.getName(),
                domain.getSerialNumber(),
                domain.getActive(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
