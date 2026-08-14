package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

@io.swagger.v3.oas.annotations.media.Schema(description = "Equipment request payload")
public record EquipmentRequestDTO(
        @NotBlank(message = "O nome do equipamento é obrigatório")
        String name,

        String serialNumber,
        Boolean active
) {
}
