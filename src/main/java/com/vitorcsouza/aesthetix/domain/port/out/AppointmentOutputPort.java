package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentOutputPort {
    Appointment save(Appointment appointment);

    Optional<Appointment> findById(UUID id);

    List<Appointment> findByProfessionalIdAndStartTimeBetween(UUID professionalId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByPatientId(UUID patientId);

    void deleteById(UUID id);
}
