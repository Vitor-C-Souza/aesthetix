package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@io.swagger.v3.oas.annotations.media.Schema(description = "Procedure request payload")
public record ProcedureRequestDTO(
        @NotBlank(message = "O nome do procedimento é obrigatório")
        String name,

        String description,

        @NotNull(message = "A duração em minutos é obrigatória")
        @Min(value = 1, message = "A duração deve ser de pelo menos 1 minuto")
        Integer durationInMinutes,

        @NotNull(message = "O preço de venda é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
        BigDecimal salePrice,

        Boolean requireEquipment,

        Boolean active
) {
}
