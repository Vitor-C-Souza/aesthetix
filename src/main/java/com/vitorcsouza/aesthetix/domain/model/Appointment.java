package com.vitorcsouza.aesthetix.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Appointment {
    private UUID id;
    private Patient patient;
    private Professional professional;
    private Procedure procedure;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AppointmentStatus status;
    private BigDecimal totalValue;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
