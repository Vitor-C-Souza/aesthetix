package com.vitorcsouza.aesthetix.domain.repository;

import com.vitorcsouza.aesthetix.domain.model.EvolutionPhoto;
import com.vitorcsouza.aesthetix.domain.model.PhotoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface EvolutionPhotoRepository extends JpaRepository<EvolutionPhoto, UUID> {
    List<EvolutionPhoto> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<EvolutionPhoto> findByAppointmentIdOrderByCreatedAtDesc(UUID appointmentId);

    List<EvolutionPhoto> findByPatientIdAndPhotoTypeOrderByCreatedAtDesc(UUID patientId, PhotoType photoType);
}
