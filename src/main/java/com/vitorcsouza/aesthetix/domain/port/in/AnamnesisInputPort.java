package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.Anamnesis;

import java.util.List;
import java.util.UUID;

public interface AnamnesisInputPort {
    Anamnesis create(Anamnesis anamnesis);

    Anamnesis update(UUID id, Anamnesis anamnesis);

    Anamnesis findById(UUID id);

    List<Anamnesis> findByPatientId(UUID patientId);

    void delete(UUID id);
}
