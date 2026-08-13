package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.PackageEntity;
import com.vitorcsouza.aesthetix.domain.model.PackageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataPackageRepository extends JpaRepository<PackageEntity, UUID> {
    List<PackageEntity> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<PackageEntity> findByPatientIdAndStatus(UUID patientId, PackageStatus status);
}
