package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.PhotoType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EvolutionPhotoRequestDTO(
        @NotNull(message = "O ID do paciente é obrigatório")
        UUID patientId,

        UUID appointmentId,

        @NotBlank(message = "A URL da foto é obrigatória")
        String photoUrl,

        @NotNull(message = "O tipo da foto é obrigatório")
        PhotoType photoType,

        String notes
) {
}
