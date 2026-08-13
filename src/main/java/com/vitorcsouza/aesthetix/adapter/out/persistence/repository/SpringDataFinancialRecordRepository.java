package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.FinancialRecordEntity;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpringDataFinancialRecordRepository extends JpaRepository<FinancialRecordEntity, UUID> {
    List<FinancialRecordEntity> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<FinancialRecordEntity> findByProfessionalIdAndStatusAndPaidAtBetween(
            UUID professionalId, PaymentStatus status, LocalDateTime start, LocalDateTime end
    );

    @Query("""
                        SELECT COALESCE(SUM(f.commissionAmount), 0) FROM FinancialRecordEntity f
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
