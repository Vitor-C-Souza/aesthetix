package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.ProcedureEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.ProcedureMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataProcedureRepository;
import com.vitorcsouza.aesthetix.domain.model.Procedure;
import com.vitorcsouza.aesthetix.domain.port.out.ProcedureOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProcedurePersistenceAdapter implements ProcedureOutputPort {
    private final SpringDataProcedureRepository repository;
    private final ProcedureMapper mapper;

    @Override
    public Procedure save(Procedure procedure) {
        ProcedureEntity entity = mapper.toEntity(procedure);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Procedure> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Procedure> findByActiveTrue() {
        return repository.findByActiveTrue().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Procedure> findByNameContainingIgnoreCaseAndActiveTrue(String name) {
        return repository.findByNameContainingIgnoreCaseAndActiveTrue(name).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
