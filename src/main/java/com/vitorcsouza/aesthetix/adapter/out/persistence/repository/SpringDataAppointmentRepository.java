package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpringDataAppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {
    List<AppointmentEntity> findByProfessionalIdAndStartTimeBetween(UUID professionalId, LocalDateTime start, LocalDateTime end);

    List<AppointmentEntity> findByPatientId(UUID patientId);
}
