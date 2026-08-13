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
public class FinancialRecord {
    private UUID id;
    private Patient patient;
    private Appointment appointment;
    private Package sessionPackage;
    private Professional professional;
    private BigDecimal amount;
    @Builder.Default
    private BigDecimal commissionAmount = BigDecimal.ZERO;
    private PaymentMethod paymentMethod;
    @Builder.Default
    private PaymentStatus status = PaymentStatus.PENDING;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
