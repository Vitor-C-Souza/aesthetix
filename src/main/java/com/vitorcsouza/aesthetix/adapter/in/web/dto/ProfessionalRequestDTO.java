package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record ProfessionalRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF em formato inválido")
        String cpf,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @NotBlank(message = "A especialidade é obrigatória")
        String specialty,

        @NotNull(message = "A taxa de comissão é obrigatória")
        BigDecimal commissionRate,

        String colorCode,

        Boolean active
) {
}
