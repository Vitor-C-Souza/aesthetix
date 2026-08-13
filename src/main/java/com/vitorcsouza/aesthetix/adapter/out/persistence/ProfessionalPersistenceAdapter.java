package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.ProfessionalEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.ProfessionalMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataProfessionalRepository;
import com.vitorcsouza.aesthetix.domain.model.Professional;
import com.vitorcsouza.aesthetix.domain.port.out.ProfessionalOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProfessionalPersistenceAdapter implements ProfessionalOutputPort {

    private final SpringDataProfessionalRepository repository;
    private final ProfessionalMapper mapper;


    @Override
    public Professional save(Professional professional) {
        ProfessionalEntity entity = mapper.toEntity(professional);
        ProfessionalEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Professional> findById(UUID id) {
        Optional<ProfessionalEntity> entity = repository.findById(id);
        return entity.map(mapper::toDomain);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public Optional<Professional> findByCpf(String cpf) {
        Optional<ProfessionalEntity> entity = repository.findByCpf(cpf);
        return entity.map(mapper::toDomain);
    }

    @Override
    public List<Professional> findByActiveTrue() {
        List<ProfessionalEntity> entities = repository.findByActiveTrue();
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Professional> findBySpecialtyIgnoreCaseAndActiveTrue(String specialty) {
        List<ProfessionalEntity> entities = repository.findBySpecialtyIgnoreCaseAndActiveTrue(specialty);
        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }
}
