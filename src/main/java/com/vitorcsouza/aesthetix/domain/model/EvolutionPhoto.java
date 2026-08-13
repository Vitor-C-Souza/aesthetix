package com.vitorcsouza.aesthetix.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EvolutionPhoto {
    private UUID id;
    private Patient patient;
    private Appointment appointment;
    private String photoUrl;
    private PhotoType photoType;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
