package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.FinancialRecord;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;

import java.util.List;
import java.util.UUID;

public interface FinancialRecordInputPort {
    FinancialRecord create(FinancialRecord record);

    FinancialRecord update(UUID id, FinancialRecord record);

    FinancialRecord findById(UUID id);

    List<FinancialRecord> findByPatientId(UUID patientId);

    List<FinancialRecord> findByStatus(PaymentStatus status);

    void delete(UUID id);
}
