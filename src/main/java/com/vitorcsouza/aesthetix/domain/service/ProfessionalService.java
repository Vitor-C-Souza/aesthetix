package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.model.Professional;
import com.vitorcsouza.aesthetix.domain.port.in.ProfessionalInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.ProfessionalOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessionalService implements ProfessionalInputPort {

    private final ProfessionalOutputPort professionalOutputPort;

    @Override
    public Professional create(Professional professional) {
        if (professionalOutputPort.existsByCpf(professional.getCpf())) {
            throw new IllegalArgumentException("Já existe um profissional cadastrado com este CPF.");
        }
        return professionalOutputPort.save(professional);
    }

    @Override
    public Professional update(UUID id, Professional updatedProfessional) {
        Professional existing = findById(id);

        existing.setName(updatedProfessional.getName());
        existing.setPhone(updatedProfessional.getPhone());
        existing.setSpecialty(updatedProfessional.getSpecialty());
        existing.setCommissionRate(updatedProfessional.getCommissionRate());
        existing.setColorCode(updatedProfessional.getColorCode());
        if (updatedProfessional.getActive() != null) {
            existing.setActive(updatedProfessional.getActive());
        }

        return professionalOutputPort.save(existing);
    }

    @Override
    public Professional findById(UUID id) {
        return professionalOutputPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado com o ID: " + id));
    }

    @Override
    public List<Professional> findAllActive() {
        return professionalOutputPort.findByActiveTrue();
    }

    @Override
    public List<Professional> findBySpecialty(String specialty) {
        return professionalOutputPort.findBySpecialtyIgnoreCaseAndActiveTrue(specialty);
    }

    @Override
    public void delete(UUID id) {
        Professional professional = findById(id);
        professionalOutputPort.deleteById(professional.getId());
    }
}
