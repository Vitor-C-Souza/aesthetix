package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Appointment;
import com.vitorcsouza.aesthetix.domain.model.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentOutputPort {
    Appointment save(Appointment appointment);

    Optional<Appointment> findById(UUID id);

    List<Appointment> findByPatientIdOrderByStartTimeDesc(UUID patientId);

    List<Appointment> findByProfessionalIdAndStartTimeBetweenOrderByStartTimeAsc(
            UUID professionalId, LocalDateTime start, LocalDateTime end
    );

    List<Appointment> findByStatusAndStartTimeBetween(
            AppointmentStatus status, LocalDateTime start, LocalDateTime end
    );

    boolean existsOverlappingAppointment(
            UUID professionalId, LocalDateTime startTime, LocalDateTime endTime, UUID appointmentId
    );

    void deleteById(UUID id);
}
