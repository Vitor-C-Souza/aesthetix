package com.vitorcsouza.aesthetix.domain.repository;

import com.vitorcsouza.aesthetix.domain.model.Package;
import com.vitorcsouza.aesthetix.domain.model.PackageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PackageRepository extends JpaRepository<Package, UUID> {
    List<Package> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<Package> findByPatientIdAndStatus(UUID patientId, PackageStatus status);
}
