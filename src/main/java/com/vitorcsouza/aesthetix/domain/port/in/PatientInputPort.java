package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.DomainPage;
import com.vitorcsouza.aesthetix.domain.DomainPageRequest;
import com.vitorcsouza.aesthetix.domain.model.Patient;

import java.util.UUID;

public interface PatientInputPort {
    Patient create(Patient patient);

    Patient update(UUID id, Patient patient);

    Patient findById(UUID id);

    DomainPage<Patient> findByName(String name, DomainPageRequest pageRequest);

    void delete(UUID id);
}
