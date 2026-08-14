package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.PaymentMethod;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Financial record response payload")
public record FinancialRecordResponseDTO(
        UUID id,
        UUID patientId,
        String patientName,
        UUID appointmentId,
        UUID packageId,
        UUID professionalId,
        String professionalName,
        BigDecimal amount,
        BigDecimal commissionAmount,
        PaymentMethod paymentMethod,
        PaymentStatus status,
        LocalDateTime paidAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
