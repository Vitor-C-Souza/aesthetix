package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.PhotoType;

import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Evolution photo response payload")
public record EvolutionPhotoResponseDTO(
        UUID id,
        UUID patientId,
        String patientName,
        UUID appointmentId,
        String photoUrl,
        PhotoType photoType,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
