package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.PatientEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.PatientMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataPatientRepository;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.model.pagination.DomainPage;
import com.vitorcsouza.aesthetix.domain.model.pagination.DomainPageRequest;
import com.vitorcsouza.aesthetix.domain.port.out.PatientOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PatientPersistenceAdapter implements PatientOutputPort {

    private final SpringDataPatientRepository repository;
    private final PatientMapper mapper;

    @Override
    public Patient save(Patient patient) {
        PatientEntity entity = mapper.toEntity(patient);
        PatientEntity savedPatient = repository.save(entity);
        return mapper.toDomain(savedPatient);
    }

    @Override
    public Optional<Patient> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Patient> findByCpf(String cpf) {
        return repository.findByCpf(cpf).map(mapper::toDomain);
    }

    @Override
    public Optional<Patient> findByPhone(String phone) {
        return repository.findByPhone(phone).map(mapper::toDomain);
    }

    @Override
    public DomainPage<Patient> findByNameContainingIgnoreCase(String name, DomainPageRequest pageRequest) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize());
        Page<Patient> page = repository.findByNameContainingIgnoreCase(name, pageable).map(mapper::toDomain);
        return new DomainPage<>(page.getContent(), page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getSize());
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
