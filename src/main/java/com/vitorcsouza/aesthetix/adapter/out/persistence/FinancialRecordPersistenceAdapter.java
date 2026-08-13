package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.FinancialRecordEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.FinancialRecordMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataFinancialRecordRepository;
import com.vitorcsouza.aesthetix.domain.model.FinancialRecord;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;
import com.vitorcsouza.aesthetix.domain.port.out.FinancialRecordOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FinancialRecordPersistenceAdapter implements FinancialRecordOutputPort {
    private final SpringDataFinancialRecordRepository repository;
    private final FinancialRecordMapper mapper;

    @Override
    public FinancialRecord save(FinancialRecord financialRecord) {
        FinancialRecordEntity entity = mapper.toEntity(financialRecord);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<FinancialRecord> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<FinancialRecord> findByPatientIdOrderByCreatedAtDesc(UUID patientId) {
        return repository.findByPatientIdOrderByCreatedAtDesc(patientId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<FinancialRecord> findByProfessionalIdAndStatusAndPaidAtBetween(
            UUID professionalId, PaymentStatus status, LocalDateTime start, LocalDateTime end
    ) {
        return repository.findByProfessionalIdAndStatusAndPaidAtBetween(professionalId, status, start, end).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public BigDecimal sumCommissionsByProfessionalAndPeriod(
            UUID professionalId, LocalDateTime start, LocalDateTime end
    ) {
        return repository.sumCommissionsByProfessionalAndPeriod(professionalId, start, end);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
