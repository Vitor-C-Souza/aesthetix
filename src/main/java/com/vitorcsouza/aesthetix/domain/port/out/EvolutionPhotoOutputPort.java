package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.EvolutionPhoto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EvolutionPhotoOutputPort {
    EvolutionPhoto save(EvolutionPhoto evolutionPhoto);

    Optional<EvolutionPhoto> findById(UUID id);

    List<EvolutionPhoto> findByPatientId(UUID patientId);

    List<EvolutionPhoto> findByAppointmentId(UUID appointmentId);

    void deleteById(UUID id);
}
