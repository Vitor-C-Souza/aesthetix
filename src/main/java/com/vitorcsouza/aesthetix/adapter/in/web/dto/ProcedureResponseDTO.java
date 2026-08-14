package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProcedureResponseDTO(
        UUID id,
        String name,
        String description,
        Integer durationInMinutes,
        BigDecimal salePrice,
        Boolean requireEquipment,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
