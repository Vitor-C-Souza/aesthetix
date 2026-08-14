package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@io.swagger.v3.oas.annotations.media.Schema(description = "Patient request payload")
public record PatientRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF em formato inválido")
        String cpf,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @Email(message = "E-mail em formato inválido")
        String email,

        LocalDate birthDate,
        String notes
) {
}
