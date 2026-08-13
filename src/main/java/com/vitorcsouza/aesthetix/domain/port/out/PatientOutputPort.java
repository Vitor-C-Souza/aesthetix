package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PatientOutputPort {
    Patient save(Patient patient);

    Optional<Patient> findById(UUID id);

    Optional<Patient> findByCpf(String cpf);

    Optional<Patient> findByPhone(String phone);

    Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

    boolean existsByCpf(String cpf);

    void deleteById(UUID id);
}
