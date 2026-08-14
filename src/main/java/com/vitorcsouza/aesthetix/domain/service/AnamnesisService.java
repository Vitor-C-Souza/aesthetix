package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.exception.ResourceNotFoundException;
import com.vitorcsouza.aesthetix.domain.model.Anamnesis;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.model.Professional;
import com.vitorcsouza.aesthetix.domain.port.in.AnamnesisInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.AnamnesisOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PatientOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.ProfessionalOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnamnesisService implements AnamnesisInputPort {

    private final AnamnesisOutputPort anamnesisOutputPort;
    private final PatientOutputPort patientOutputPort;
    private final ProfessionalOutputPort professionalOutputPort;

    @Override
    public Anamnesis create(Anamnesis anamnesis) {
        Patient patient = patientOutputPort.findById(anamnesis.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado."));

        Professional professional = professionalOutputPort.findById(anamnesis.getProfessional().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado."));

        anamnesis.setPatient(patient);
        anamnesis.setProfessional(professional);

        return anamnesisOutputPort.save(anamnesis);
    }

    @Override
    public Anamnesis update(UUID id, Anamnesis updatedAnamnesis) {
        Anamnesis existing = findById(id);

        existing.setFormData(updatedAnamnesis.getFormData());
        existing.setSignatureUrl(updatedAnamnesis.getSignatureUrl());

        return anamnesisOutputPort.save(existing);
    }

    @Override
    public Anamnesis findById(UUID id) {
        return anamnesisOutputPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anamnese não encontrada com o ID: " + id));
    }

    @Override
    public List<Anamnesis> findByPatientId(UUID patientId) {
        return anamnesisOutputPort.findByPatientId(patientId);
    }

    @Override
    public void delete(UUID id) {
        Anamnesis anamnesis = findById(id);
        anamnesisOutputPort.deleteById(anamnesis.getId());
    }
}
