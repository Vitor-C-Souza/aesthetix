package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.AnamnesisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataAnamnesisRepository extends JpaRepository<AnamnesisEntity, UUID> {
    List<AnamnesisEntity> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    Optional<AnamnesisEntity> findFirstByPatientIdOrderByCreatedAtDesc(UUID patientId);
}
