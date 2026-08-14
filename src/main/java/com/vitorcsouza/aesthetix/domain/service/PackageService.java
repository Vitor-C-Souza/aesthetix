package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.exception.ResourceNotFoundException;
import com.vitorcsouza.aesthetix.domain.model.Package;
import com.vitorcsouza.aesthetix.domain.model.PackageStatus;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.model.Procedure;
import com.vitorcsouza.aesthetix.domain.port.in.PackageInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PackageOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PatientOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.ProcedureOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PackageService implements PackageInputPort {

    private final PackageOutputPort packageOutputPort;
    private final PatientOutputPort patientOutputPort;
    private final ProcedureOutputPort procedureOutputPort;

    @Override
    public Package create(Package sessionPackage) {
        Patient patient = patientOutputPort.findById(sessionPackage.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado."));

        Procedure procedure = procedureOutputPort.findById(sessionPackage.getProcedure().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado."));

        sessionPackage.setPatient(patient);
        sessionPackage.setProcedure(procedure);

        return packageOutputPort.save(sessionPackage);
    }

    @Override
    public Package update(UUID id, Package updatedPackage) {
        Package existing = findById(id);

        existing.setTotalSessions(updatedPackage.getTotalSessions());
        existing.setTotalPrice(updatedPackage.getTotalPrice());

        if (updatedPackage.getUsedSessions() != null) {
            existing.setUsedSessions(updatedPackage.getUsedSessions());
        }

        if (updatedPackage.getStatus() != null) {
            existing.setStatus(updatedPackage.getStatus());
        }

        return packageOutputPort.save(existing);
    }

    @Override
    public Package findById(UUID id) {
        return packageOutputPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado com o ID: " + id));
    }

    @Override
    public List<Package> findByPatientId(UUID patientId) {
        return packageOutputPort.findByPatientId(patientId);
    }

    @Override
    public Package consumeSession(UUID id) {
        Package sessionPackage = findById(id);

        if (PackageStatus.COMPLETED.equals(sessionPackage.getStatus()) ||
                sessionPackage.getUsedSessions() >= sessionPackage.getTotalSessions()) {
            throw new IllegalStateException("Todas as sessões deste pacote já foram consumidas.");
        }

        sessionPackage.setUsedSessions(sessionPackage.getUsedSessions() + 1);

        if (sessionPackage.getUsedSessions().equals(sessionPackage.getTotalSessions())) {
            sessionPackage.setStatus(PackageStatus.COMPLETED);
        }

        return packageOutputPort.save(sessionPackage);
    }

    @Override
    public void delete(UUID id) {
        Package sessionPackage = findById(id);
        packageOutputPort.deleteById(sessionPackage.getId());
    }
}
