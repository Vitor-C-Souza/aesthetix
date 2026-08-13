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
public class Procedure {
    private UUID id;
    private String name;
    private String description;
    private Integer durationInMinutes;
    private BigDecimal salePrice;
    @Builder.Default
    private Boolean requireEquipment = false;
    @Builder.Default
    private Boolean active = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
