package com.vitorcsouza.aesthetix.domain.repository;

import com.vitorcsouza.aesthetix.domain.model.Anamnesis;
import com.vitorcsouza.aesthetix.domain.model.PackageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface PackageRepository extends JpaRepository<Anamnesis, UUID> {
    List<Package> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<Package> findByPatientIdAndStatus(UUID patientId, PackageStatus status);
}
