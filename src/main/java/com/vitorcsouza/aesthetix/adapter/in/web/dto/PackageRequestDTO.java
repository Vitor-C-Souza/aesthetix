package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.PackageStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Package request payload", example = "{\"patientId\":\"00000000-0000-0000-0000-000000000000\",\"sessions\":10,\"price\":499.0}")
public record PackageRequestDTO(
        @NotNull(message = "O ID do paciente é obrigatório")
        UUID patientId,

        @NotNull(message = "O ID do procedimento é obrigatório")
        UUID procedureId,

        @NotNull(message = "O total de sessões é obrigatório")
        @Positive(message = "O total de sessões deve ser maior que zero")
        Integer totalSessions,

        Integer usedSessions,

        @NotNull(message = "O preço total é obrigatório")
        @Positive(message = "O preço total deve ser maior que zero")
        BigDecimal totalPrice,

        PackageStatus status
) {
}
