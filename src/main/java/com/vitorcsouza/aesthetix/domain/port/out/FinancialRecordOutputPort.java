package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.FinancialRecord;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FinancialRecordOutputPort {
    FinancialRecord save(FinancialRecord financialRecord);

    Optional<FinancialRecord> findById(UUID id);

    List<FinancialRecord> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<FinancialRecord> findByProfessionalIdAndStatusAndPaidAtBetween(
            UUID professionalId, PaymentStatus status, LocalDateTime start, LocalDateTime end
    );

    BigDecimal sumCommissionsByProfessionalAndPeriod(
            UUID professionalId, LocalDateTime start, LocalDateTime end
    );

    void deleteById(UUID id);
}
