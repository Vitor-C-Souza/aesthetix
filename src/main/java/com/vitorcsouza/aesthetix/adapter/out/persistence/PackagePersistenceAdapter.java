package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.PackageMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataPackageRepository;
import com.vitorcsouza.aesthetix.domain.model.Package;
import com.vitorcsouza.aesthetix.domain.port.out.PackageOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PackagePersistenceAdapter implements PackageOutputPort {
    private final SpringDataPackageRepository repository;
    private final PackageMapper mapper;

    @Override
    public Package save(Package sessionPackage) {
        var entity = mapper.toEntity(sessionPackage);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Package> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Package> findByPatientId(UUID patientId) {
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
