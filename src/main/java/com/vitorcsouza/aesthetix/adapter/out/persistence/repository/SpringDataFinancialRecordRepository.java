package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.FinancialRecordEntity;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataFinancialRecordRepository extends JpaRepository<FinancialRecordEntity, UUID> {
    List<FinancialRecordEntity> findByPatientId(UUID patientId);

    List<FinancialRecordEntity> findByStatus(PaymentStatus status);
}
