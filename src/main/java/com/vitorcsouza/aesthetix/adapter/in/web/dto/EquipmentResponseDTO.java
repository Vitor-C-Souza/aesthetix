package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Equipment response payload", example = "{\"id\":\"00000000-0000-0000-0000-000000000000\",\"name\":\"Laser 2000\",\"serialNumber\":\"SN12345\",\"active\":true}")
public record EquipmentResponseDTO(
        UUID id,
        String name,
        String serialNumber,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
