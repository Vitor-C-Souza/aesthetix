package com.vitorcsouza.aesthetix.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Package {
    private UUID id;
    private Patient patient;
    private Procedure procedure;
    private Integer totalSessions;
    @Builder.Default
    private Integer usedSessions = 0;
    private BigDecimal totalPrice;
    @Builder.Default
    private PackageStatus status = PackageStatus.ACTIVE;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
