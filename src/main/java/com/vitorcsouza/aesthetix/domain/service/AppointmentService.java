package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.model.*;
import com.vitorcsouza.aesthetix.domain.port.in.AppointmentInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.AppointmentOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PatientOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.ProcedureOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.ProfessionalOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService implements AppointmentInputPort {

    private final AppointmentOutputPort appointmentOutputPort;
    private final PatientOutputPort patientOutputPort;
    private final ProfessionalOutputPort professionalOutputPort;
    private final ProcedureOutputPort procedureOutputPort;

    @Override
    public Appointment create(Appointment appointment) {
        validateTimeRange(appointment.getStartTime(), appointment.getEndTime());

        Patient patient = patientOutputPort.findById(appointment.getPatient().getId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado."));
        Professional professional = professionalOutputPort.findById(appointment.getProfessional().getId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado."));
        Procedure procedure = procedureOutputPort.findById(appointment.getProcedure().getId())
                .orElseThrow(() -> new IllegalArgumentException("Procedimento não encontrado."));

        appointment.setPatient(patient);
        appointment.setProfessional(professional);
        appointment.setProcedure(procedure);

        return appointmentOutputPort.save(appointment);
    }

    @Override
    public Appointment update(UUID id, Appointment updatedAppointment) {
        Appointment existing = findById(id);
        validateTimeRange(updatedAppointment.getStartTime(), updatedAppointment.getEndTime());

        existing.setStartTime(updatedAppointment.getStartTime());
        existing.setEndTime(updatedAppointment.getEndTime());
        existing.setTotalValue(updatedAppointment.getTotalValue());
        existing.setNotes(updatedAppointment.getNotes());

        if (updatedAppointment.getStatus() != null) {
            existing.setStatus(updatedAppointment.getStatus());
        }

        return appointmentOutputPort.save(existing);
    }

    @Override
    public Appointment updateStatus(UUID id, AppointmentStatus status) {
        Appointment existing = findById(id);
        existing.setStatus(status);
        return appointmentOutputPort.save(existing);
    }

    @Override
    public Appointment findById(UUID id) {
        return appointmentOutputPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com o ID: " + id));
    }

    @Override
    public List<Appointment> findByProfessionalAndPeriod(UUID professionalId, LocalDateTime start, LocalDateTime end) {
        return appointmentOutputPort.findByProfessionalIdAndStartTimeBetween(professionalId, start, end);
    }

    @Override
    public List<Appointment> findByPatient(UUID patientId) {
        return appointmentOutputPort.findByPatientId(patientId);
    }

    @Override
    public void cancel(UUID id) {
        Appointment appointment = findById(id);
        appointment.setStatus(AppointmentStatus.CANCELED);
        appointmentOutputPort.save(appointment);
    }

    private void validateTimeRange(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null || !end.isAfter(start)) {
            throw new IllegalArgumentException("A data/hora final deve ser posterior à data/hora inicial.");
        }
    }
}
