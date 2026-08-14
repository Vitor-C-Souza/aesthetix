package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.Procedure;

import java.util.List;
import java.util.UUID;

public interface ProcedureInputPort {
    Procedure create(Procedure procedure);

    Procedure update(UUID id, Procedure procedure);

    Procedure findById(UUID id);

    List<Procedure> findAllActive();

    void delete(UUID id);
}
