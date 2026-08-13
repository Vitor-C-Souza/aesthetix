package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Professional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionalOutputPort {
    Professional save(Professional professional);

    void deleteById(UUID id);

    Optional<Professional> findById(UUID id);

    boolean existsByCpf(String cpf);

    Optional<Professional> findByCpf(String cpf);

    List<Professional> findByActiveTrue();

    List<Professional> findBySpecialtyIgnoreCaseAndActiveTrue(String specialty);

}
