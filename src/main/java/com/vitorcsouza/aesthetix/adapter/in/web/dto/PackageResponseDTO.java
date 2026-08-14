package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.PackageStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Package response payload")
public record PackageResponseDTO(
        UUID id,
        UUID patientId,
        String patientName,
        UUID procedureId,
        String procedureName,
        Integer totalSessions,
        Integer usedSessions,
        BigDecimal totalPrice,
        PackageStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
