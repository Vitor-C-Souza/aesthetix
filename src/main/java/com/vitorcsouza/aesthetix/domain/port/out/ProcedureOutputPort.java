package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Procedure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProcedureOutputPort {
    Procedure save(Procedure procedure);

    Optional<Procedure> findById(UUID id);

    List<Procedure> findByActiveTrue();

    List<Procedure> findByNameContainingIgnoreCaseAndActiveTrue(String name);

    void deleteById(UUID id);
}
