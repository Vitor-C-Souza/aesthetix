package com.vitorcsouza.aesthetix.domain.repository;

import com.vitorcsouza.aesthetix.domain.model.FinancialRecord;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, UUID> {
    List<FinancialRecord> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<FinancialRecord> findByProfessionalIdAndStatusAndPaidAtBetween(
            UUID professionalId, PaymentStatus status, LocalDateTime start, LocalDateTime end
    );

    // Soma o total de comissões pagas a um profissional em determinado período
    @Query("""
        SELECT COALESCE(SUM(f.commissionAmount), 0) FROM FinancialRecord f
        WHERE f.professional.id = :professionalId
          AND f.status = 'PAID'
          AND f.paidAt BETWEEN :start AND :end
    """)
    BigDecimal sumCommissionsByProfessionalAndPeriod(
            @Param("professionalId") UUID professionalId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
