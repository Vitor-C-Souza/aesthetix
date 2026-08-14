package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.EvolutionPhoto;

import java.util.List;
import java.util.UUID;

public interface EvolutionPhotoInputPort {
    EvolutionPhoto create(EvolutionPhoto evolutionPhoto);

    EvolutionPhoto update(UUID id, EvolutionPhoto evolutionPhoto);

    EvolutionPhoto findById(UUID id);

    List<EvolutionPhoto> findByPatientId(UUID patientId);

    List<EvolutionPhoto> findByAppointmentId(UUID appointmentId);

    void delete(UUID id);
}
