package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.ProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataProcedureRepository extends JpaRepository<ProcedureEntity, UUID> {
    List<ProcedureEntity> findByActiveTrue();

    List<ProcedureEntity> findByNameContainingIgnoreCaseAndActiveTrue(String name);
}
