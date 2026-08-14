package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.Appointment;
import com.vitorcsouza.aesthetix.domain.model.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentInputPort {
    Appointment create(Appointment appointment);

    Appointment update(UUID id, Appointment appointment);

    Appointment updateStatus(UUID id, AppointmentStatus status);

    Appointment findById(UUID id);

    List<Appointment> findByProfessionalAndPeriod(UUID professionalId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByPatient(UUID patientId);

    void cancel(UUID id);
}
