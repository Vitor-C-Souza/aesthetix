package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
