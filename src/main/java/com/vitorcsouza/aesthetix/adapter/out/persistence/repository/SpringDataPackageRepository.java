package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataPackageRepository extends JpaRepository<PackageEntity, UUID> {
    List<PackageEntity> findByPatientId(UUID patientId);
}
