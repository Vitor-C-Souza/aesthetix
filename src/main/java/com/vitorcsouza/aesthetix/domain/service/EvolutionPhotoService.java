package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.exception.ResourceNotFoundException;
import com.vitorcsouza.aesthetix.domain.model.Appointment;
import com.vitorcsouza.aesthetix.domain.model.EvolutionPhoto;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.port.in.EvolutionPhotoInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.AppointmentOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.EvolutionPhotoOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PatientOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class EvolutionPhotoService implements EvolutionPhotoInputPort {

    private final EvolutionPhotoOutputPort photoOutputPort;
    private final PatientOutputPort patientOutputPort;
    private final AppointmentOutputPort appointmentOutputPort;

    @Override
    public EvolutionPhoto create(EvolutionPhoto photo) {
        Patient patient = patientOutputPort.findById(photo.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado."));

        photo.setPatient(patient);

        if (photo.getAppointment() != null && photo.getAppointment().getId() != null) {
            Appointment appointment = appointmentOutputPort.findById(photo.getAppointment().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado."));
            photo.setAppointment(appointment);
        }

        return photoOutputPort.save(photo);
    }

    @Override
    public EvolutionPhoto update(UUID id, EvolutionPhoto updatedPhoto) {
        EvolutionPhoto existing = findById(id);

        existing.setPhotoUrl(updatedPhoto.getPhotoUrl());
        existing.setPhotoType(updatedPhoto.getPhotoType());
        existing.setNotes(updatedPhoto.getNotes());

        return photoOutputPort.save(existing);
    }

    @Override
    public EvolutionPhoto findById(UUID id) {
        return photoOutputPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Foto de evolução não encontrada com o ID: " + id));
    }

    @Override
    public List<EvolutionPhoto> findByPatientId(UUID patientId) {
        return photoOutputPort.findByPatientId(patientId);
    }

    @Override
    public List<EvolutionPhoto> findByAppointmentId(UUID appointmentId) {
        return photoOutputPort.findByAppointmentId(appointmentId);
    }

    @Override
    public void delete(UUID id) {
        EvolutionPhoto photo = findById(id);
        photoOutputPort.deleteById(photo.getId());
    }
}
