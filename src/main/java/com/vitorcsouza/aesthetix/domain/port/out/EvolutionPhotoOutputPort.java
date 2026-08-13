package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.EvolutionPhoto;
import com.vitorcsouza.aesthetix.domain.model.PhotoType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EvolutionPhotoOutputPort {
    EvolutionPhoto save(EvolutionPhoto evolutionPhoto);

    Optional<EvolutionPhoto> findById(UUID id);

    List<EvolutionPhoto> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<EvolutionPhoto> findByAppointmentIdOrderByCreatedAtDesc(UUID appointmentId);

    List<EvolutionPhoto> findByPatientIdAndPhotoTypeOrderByCreatedAtDesc(UUID patientId, PhotoType photoType);

    void deleteById(UUID id);
}
