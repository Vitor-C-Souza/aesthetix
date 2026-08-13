package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.AppointmentEntity;
import com.vitorcsouza.aesthetix.domain.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SpringDataAppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {
    List<AppointmentEntity> findByPatientIdOrderByStartTimeDesc(UUID patientId);

    List<AppointmentEntity> findByProfessionalIdAndStartTimeBetweenOrderByStartTimeAsc(
            UUID professionalId, LocalDateTime start, LocalDateTime end
    );

    List<AppointmentEntity> findByStatusAndStartTimeBetween(
            AppointmentStatus status, LocalDateTime start, LocalDateTime end
    );

    @Query("""
                        SELECT COUNT(a) > 0 FROM AppointmentEntity a
                WHERE a.professional.id = :professionalId
                  AND a.status <> 'CANCELED'
                  AND (:appointmentId IS NULL OR a.id <> :appointmentId)
                  AND a.startTime < :endTime
                  AND a.endTime > :startTime
            """)
    boolean existsOverlappingAppointment(
            @Param("professionalId") UUID professionalId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("appointmentId") UUID appointmentId
    );
}
