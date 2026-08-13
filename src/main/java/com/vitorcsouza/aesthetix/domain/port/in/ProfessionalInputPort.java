package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.Professional;

import java.util.List;
import java.util.UUID;

public interface ProfessionalInputPort {
    Professional create(Professional professional);

    Professional update(UUID id, Professional professional);

    Professional findById(UUID id);

    List<Professional> findAllActive();

    List<Professional> findBySpecialty(String specialty);

    void delete(UUID id);
}
