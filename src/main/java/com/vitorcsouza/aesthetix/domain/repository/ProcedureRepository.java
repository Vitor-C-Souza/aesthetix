package com.vitorcsouza.aesthetix.domain.repository;

import com.vitorcsouza.aesthetix.domain.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface ProcedureRepository extends JpaRepository<Procedure, UUID> {
    List<Procedure> findByActiveTrue();

    List<Procedure> findByNameContainingIgnoreCaseAndActiveTrue(String name);
}
