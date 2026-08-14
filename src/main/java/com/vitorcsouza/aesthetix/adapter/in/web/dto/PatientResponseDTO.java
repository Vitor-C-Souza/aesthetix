package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Patient response payload")
public record PatientResponseDTO(
        UUID id,
        String name,
        String cpf,
        String phone,
        String email,
        LocalDate birthDate,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
