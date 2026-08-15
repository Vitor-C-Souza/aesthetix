package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.exception.ResourceNotFoundException;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.model.pagination.DomainPage;
import com.vitorcsouza.aesthetix.domain.model.pagination.DomainPageRequest;
import com.vitorcsouza.aesthetix.domain.port.in.PatientInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PatientOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class PatientService implements PatientInputPort {

    private final PatientOutputPort patientOutputPort;

    @Override
    public Patient create(Patient patient) {
        if (patientOutputPort.existsByCpf(patient.getCpf())) {
            throw new ResourceNotFoundException("Já existe um paciente cadastrado com este CPF.");
        }
        return patientOutputPort.save(patient);
    }

    @Override
    public Patient update(UUID id, Patient updatedPatient) {
        Patient existing = findById(id);

        existing.setName(updatedPatient.getName());
        existing.setPhone(updatedPatient.getPhone());
        existing.setEmail(updatedPatient.getEmail());
        existing.setBirthDate(updatedPatient.getBirthDate());
        existing.setNotes(updatedPatient.getNotes());

        return patientOutputPort.save(existing);
    }

    @Override
    public Patient findById(UUID id) {
        return patientOutputPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + id));
    }

    @Override
    public DomainPage<Patient> findByName(String name, DomainPageRequest pageRequest) {
        return patientOutputPort.findByNameContainingIgnoreCase(name, pageRequest);
    }

    @Override
    public void delete(UUID id) {
        Patient patient = findById(id);
        patientOutputPort.deleteById(patient.getId());
    }
}
