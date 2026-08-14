package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.PaymentMethod;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@io.swagger.v3.oas.annotations.media.Schema(description = "Financial record request payload", example = "{\"patientId\":\"00000000-0000-0000-0000-000000000000\",\"amount\":99.90,\"paymentMethod\":\"CREDIT_CARD\"}")
public record FinancialRecordRequestDTO(
        @NotNull(message = "O ID do paciente é obrigatório")
        UUID patientId,

        UUID appointmentId,
        UUID packageId,
        UUID professionalId,

        @NotNull(message = "O valor é obrigatório")
        @Positive(message = "O valor deve ser maior que zero")
        BigDecimal amount,

        BigDecimal commissionAmount,

        @NotNull(message = "A forma de pagamento é obrigatória")
        PaymentMethod paymentMethod,

        PaymentStatus status,
        LocalDateTime paidAt
) {
}
