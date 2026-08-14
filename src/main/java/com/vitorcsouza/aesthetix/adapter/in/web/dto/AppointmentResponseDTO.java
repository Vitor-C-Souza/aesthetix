package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.AppointmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Appointment response payload", example = "{\"id\":\"00000000-0000-0000-0000-000000000000\",\"patientId\":\"00000000-0000-0000-0000-000000000000\",\"professionalId\":\"00000000-0000-0000-0000-000000000000\",\"start\":\"2026-08-20T10:00:00\",\"end\":\"2026-08-20T11:00:00\",\"status\":\"SCHEDULED\"}")
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
