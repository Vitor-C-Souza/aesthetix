package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Equipment response payload")
public record EquipmentResponseDTO(
        UUID id,
        String name,
        String serialNumber,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
