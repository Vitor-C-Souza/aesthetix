package com.vitorcsouza.aesthetix.domain.repository;

import com.vitorcsouza.aesthetix.domain.model.Appointment;
import com.vitorcsouza.aesthetix.domain.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByPatientIdOrderByStartTimeDesc(UUID patientId);

    List<Appointment> findByProfessionalIdAndStartTimeBetweenOrderByStartTimeAsc(
            UUID professionalId, LocalDateTime start, LocalDateTime end
    );

    List<Appointment> findByStatusAndStartTimeBetween(
            AppointmentStatus status, LocalDateTime start, LocalDateTime end
    );

    // Valida se já existe agendamento no mesmo horário para o mesmo profissional (ignora cancelados)
    @Query("""
        SELECT COUNT(a) > 0 FROM Appointment a
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
