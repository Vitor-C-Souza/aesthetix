package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.DomainPage;
import com.vitorcsouza.aesthetix.domain.DomainPageRequest;
import com.vitorcsouza.aesthetix.domain.model.Patient;

import java.util.Optional;
import java.util.UUID;

public interface PatientOutputPort {
    Patient save(Patient patient);

    Optional<Patient> findById(UUID id);

    Optional<Patient> findByCpf(String cpf);

    Optional<Patient> findByPhone(String phone);

    DomainPage<Patient> findByNameContainingIgnoreCase(String name, DomainPageRequest pageRequest);

    boolean existsByCpf(String cpf);

    void deleteById(UUID id);
}
