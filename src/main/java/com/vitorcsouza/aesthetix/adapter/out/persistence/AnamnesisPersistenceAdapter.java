package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.AnamnesisMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataAnamnesisRepository;
import com.vitorcsouza.aesthetix.domain.model.Anamnesis;
import com.vitorcsouza.aesthetix.domain.port.out.AnamnesisOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AnamnesisPersistenceAdapter implements AnamnesisOutputPort {
    private final SpringDataAnamnesisRepository repository;
    private final AnamnesisMapper mapper;

    @Override
    public Anamnesis save(Anamnesis anamnesis) {
        var entity = mapper.toEntity(anamnesis);
        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Anamnesis> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Anamnesis> findByPatientId(UUID patientId) {
        return repository.findByPatientId(patientId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
