package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.EvolutionPhotoEntity;
import com.vitorcsouza.aesthetix.domain.model.PhotoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataEvolutionPhotoRepository extends JpaRepository<EvolutionPhotoEntity, UUID> {
    List<EvolutionPhotoEntity> findByPatientIdOrderByCreatedAtDesc(UUID patientId);

    List<EvolutionPhotoEntity> findByAppointmentIdOrderByCreatedAtDesc(UUID appointmentId);

    List<EvolutionPhotoEntity> findByPatientIdAndPhotoTypeOrderByCreatedAtDesc(UUID patientId, PhotoType photoType);
}
