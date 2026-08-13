package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Package;
import com.vitorcsouza.aesthetix.domain.model.PackageStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PackageOutputPort {
    Package save(Package sessionPackage);

    Optional<Package> findById(UUID id);

    List<Package> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<Package> findByPatientIdAndStatus(UUID patientId, PackageStatus status);

    void deleteById(UUID id);
}
