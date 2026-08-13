package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.port.in.PatientInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PatientOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService implements PatientInputPort {

    private final PatientOutputPort patientOutputPort;

    @Override
    public Patient create(Patient patient) {
        if (patientOutputPort.existsByCpf(patient.getCpf())) {
            throw new IllegalArgumentException("Já existe um paciente cadastrado com este CPF.");
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
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com o ID: " + id));
    }

    @Override
    public Page<Patient> findByName(String name, Pageable pageable) {
        return patientOutputPort.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public void delete(UUID id) {
        Patient patient = findById(id);
        patientOutputPort.deleteById(patient.getId());
    }
}
