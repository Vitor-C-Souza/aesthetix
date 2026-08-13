package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.ProfessionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataProfessionalRepository extends JpaRepository<ProfessionalEntity, UUID> {
    Optional<ProfessionalEntity> findByCpf(String cpf);

    List<ProfessionalEntity> findByActiveTrue();

    List<ProfessionalEntity> findBySpecialtyIgnoreCaseAndActiveTrue(String specialty);

    boolean existsByCpf(String cpf);
}
