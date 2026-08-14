package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.FinancialRecordMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataFinancialRecordRepository;
import com.vitorcsouza.aesthetix.domain.model.FinancialRecord;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;
import com.vitorcsouza.aesthetix.domain.port.out.FinancialRecordOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        var entity = mapper.toEntity(financialRecord);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<FinancialRecord> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<FinancialRecord> findByPatientId(UUID patientId) {
        return repository.findByPatientId(patientId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<FinancialRecord> findByStatus(PaymentStatus status) {
        return repository.findByStatus(status)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }


    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
