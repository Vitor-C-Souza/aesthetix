package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.AppointmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponseDTO(
        UUID id,
        UUID patientId,
        String patientName,
        UUID professionalId,
        String professionalName,
        UUID procedureId,
        String procedureName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        AppointmentStatus status,
        BigDecimal totalValue,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
