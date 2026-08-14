package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.exception.BusinessException;
import com.vitorcsouza.aesthetix.domain.exception.ResourceNotFoundException;
import com.vitorcsouza.aesthetix.domain.model.*;
import com.vitorcsouza.aesthetix.domain.port.in.AppointmentInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.AppointmentOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PatientOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.ProcedureOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.ProfessionalOutputPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + appointment.getPatient().getId()));
        Professional professional = professionalOutputPort.findById(appointment.getProfessional().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com o ID: " + appointment.getProfessional().getId()));
        Procedure procedure = procedureOutputPort.findById(appointment.getProcedure().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado com o ID: " + appointment.getProcedure().getId()));

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
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com o ID: " + id));
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
            throw new BusinessException("A data/hora final deve ser posterior à data/hora inicial.");
        }
    }
}
