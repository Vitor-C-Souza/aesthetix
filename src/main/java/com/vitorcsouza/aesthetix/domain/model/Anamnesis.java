package com.vitorcsouza.aesthetix.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Anamnesis {
    private UUID id;
    private Patient patient;
    private Professional professional;
    private String formData;
    private String signatureUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
