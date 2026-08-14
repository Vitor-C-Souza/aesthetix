package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Anamnesis response payload", example = "{\"id\":\"00000000-0000-0000-0000-000000000000\",\"patientId\":\"00000000-0000-0000-0000-000000000000\",\"notes\":\"Patient reports ...\"}")
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
