package com.vitorcsouza.aesthetix.domain.repository;

import com.vitorcsouza.aesthetix.domain.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface ProfessionalRepository extends JpaRepository<Professional, UUID> {
    Optional<Professional> findByCpf(String cpf);

    List<Professional> findByActiveTrue();

    List<Professional> findBySpecialtyIgnoreCaseAndActiveTrue(String specialty);

    boolean existsByCpf(String cpf);
}
