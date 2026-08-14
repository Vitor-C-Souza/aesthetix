package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.AppointmentStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Appointment request payload", example = "{\"patientId\":\"00000000-0000-0000-0000-000000000000\",\"professionalId\":\"00000000-0000-0000-0000-000000000000\",\"start\":\"2026-08-20T10:00:00\",\"end\":\"2026-08-20T11:00:00\",\"notes\":\"Follow-up\"}")
public record AppointmentRequestDTO(
        @NotNull(message = "O ID do paciente é obrigatório")
        UUID patientId,

        @NotNull(message = "O ID do profissional é obrigatório")
        UUID professionalId,

        @NotNull(message = "O ID do procedimento é obrigatório")
        UUID procedureId,

        @NotNull(message = "A data/hora de início é obrigatória")
        LocalDateTime startTime,

        @NotNull(message = "A data/hora de término é obrigatória")
        LocalDateTime endTime,

        AppointmentStatus status,

        @NotNull(message = "O valor total é obrigatório")
        @DecimalMin(value = "0.0", message = "O valor total não pode ser negativo")
        BigDecimal totalValue,

        String notes
) {
}
