package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnamnesisResponseDTO(
        UUID id,
        UUID patientId,
        String patientName,
        UUID professionalId,
        String professionalName,
        String formData,
        String signatureUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
