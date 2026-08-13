package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.PackageEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.PackageMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataPackageRepository;
import com.vitorcsouza.aesthetix.domain.model.Package;
import com.vitorcsouza.aesthetix.domain.model.PackageStatus;
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
        PackageEntity entity = mapper.toEntity(sessionPackage);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Package> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Package> findByPatientIdOrderByCreatedAtDesc(UUID patientId) {
        return repository.findByPatientIdOrderByCreatedAtDesc(patientId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Package> findByPatientIdAndStatus(UUID patientId, PackageStatus status) {
        return repository.findByPatientIdAndStatus(patientId, status).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
