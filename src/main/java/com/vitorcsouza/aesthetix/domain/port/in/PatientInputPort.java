package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PatientInputPort {
    Patient create(Patient patient);

    Patient update(UUID id, Patient patient);

    Patient findById(UUID id);

    Page<Patient> findByName(String name, Pageable pageable);

    void delete(UUID id);
}
