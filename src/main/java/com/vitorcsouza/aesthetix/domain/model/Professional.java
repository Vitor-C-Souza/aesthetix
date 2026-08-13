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
public class Professional {
    private UUID id;
    private String name;
    private String cpf;
    private String phone;
    private String specialty;
    private BigDecimal commissionRate;
    private String colorCode;
    @Builder.Default
    private Boolean active = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
