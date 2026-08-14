package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record EquipmentRequestDTO(
        @NotBlank(message = "O nome do equipamento é obrigatório")
        String name,

        String serialNumber,
        Boolean active
) {
}
