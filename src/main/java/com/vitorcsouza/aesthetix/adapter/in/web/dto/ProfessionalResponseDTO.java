package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProfessionalResponseDTO(
        UUID id,
        String name,
        String cpf,
        String phone,
        String specialty,
        BigDecimal commissionRate,
        String colorCode,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
