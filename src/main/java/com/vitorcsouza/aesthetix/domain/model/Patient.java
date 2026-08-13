package com.vitorcsouza.aesthetix.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Patient {
    private UUID id;
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
