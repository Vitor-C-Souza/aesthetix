package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Anamnesis;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnamnesisOutputPort {
    Anamnesis save(Anamnesis anamnesis);

    Optional<Anamnesis> findById(UUID id);

    List<Anamnesis> findByPatientId(UUID patientId);

    void deleteById(UUID id);
}
