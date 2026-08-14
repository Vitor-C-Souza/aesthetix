package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.FinancialRecord;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FinancialRecordOutputPort {
    FinancialRecord save(FinancialRecord record);

    Optional<FinancialRecord> findById(UUID id);

    List<FinancialRecord> findByPatientId(UUID patientId);

    List<FinancialRecord> findByStatus(PaymentStatus status);

    void deleteById(UUID id);
}
