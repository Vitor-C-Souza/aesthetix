package com.vitorcsouza.aesthetix.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Equipment {
    private UUID id;
    private String name;
    private String serialNumber;
    @Builder.Default
    private Boolean active = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
