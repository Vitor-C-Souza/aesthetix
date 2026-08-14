package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Anamnesis request payload", example = "{\"patientId\":\"00000000-0000-0000-0000-000000000000\",\"notes\":\"Patient reports ...\"}")
public record AnamnesisRequestDTO(
        @NotNull(message = "O ID do paciente é obrigatório")
        UUID patientId,

        @NotNull(message = "O ID do profissional é obrigatório")
        UUID professionalId,

        @NotBlank(message = "Os dados do formulário são obrigatórios")
        String formData,

        String signatureUrl
) {
}
