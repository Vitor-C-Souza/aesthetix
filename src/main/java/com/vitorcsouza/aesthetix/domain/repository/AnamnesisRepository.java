package com.vitorcsouza.aesthetix.domain.repository;

import com.vitorcsouza.aesthetix.domain.model.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface AnamnesisRepository extends JpaRepository<Anamnesis, UUID> {
    List<Anamnesis> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    Optional<Anamnesis> findFirstByPatientIdOrderByCreatedAtDesc(UUID patientId);
}
