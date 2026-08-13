package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.EvolutionPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataEvolutionPhotoRepository extends JpaRepository<EvolutionPhotoEntity, UUID> {
    List<EvolutionPhotoEntity> findByPatientId(UUID patientId);

    List<EvolutionPhotoEntity> findByAppointmentId(UUID appointmentId);
}
