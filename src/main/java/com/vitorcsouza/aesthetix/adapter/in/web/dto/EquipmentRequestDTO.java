package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

@io.swagger.v3.oas.annotations.media.Schema(description = "Equipment request payload", example = "{\"name\":\"Laser 2000\",\"serialNumber\":\"SN12345\",\"active\":true}")
public record EquipmentRequestDTO(
        @NotBlank(message = "O nome do equipamento é obrigatório")
        String name,

        String serialNumber,
        Boolean active
) {
}
