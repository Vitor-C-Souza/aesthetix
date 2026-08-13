package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPatientRepository extends JpaRepository<PatientEntity, UUID> {
    Optional<PatientEntity> findByCpf(String cpf);

    Optional<PatientEntity> findByPhone(String phone);

    Page<PatientEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByCpf(String cpf);
}
