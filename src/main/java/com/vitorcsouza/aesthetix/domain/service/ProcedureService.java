package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.model.Procedure;
import com.vitorcsouza.aesthetix.domain.port.in.ProcedureInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.ProcedureOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcedureService implements ProcedureInputPort {

    private final ProcedureOutputPort procedureOutputPort;

    @Override
    public Procedure create(Procedure procedure) {
        return procedureOutputPort.save(procedure);
    }

    @Override
    public Procedure update(UUID id, Procedure procedure) {
        Procedure existing = findById(id);

        existing.setName(procedure.getName());
        existing.setDescription(procedure.getDescription());
        existing.setDurationInMinutes(procedure.getDurationInMinutes());
        existing.setSalePrice(procedure.getSalePrice());

        if (procedure.getRequireEquipment() != null) {
            existing.setRequireEquipment(procedure.getRequireEquipment());
        }
        if (procedure.getActive() != null) {
            existing.setActive(procedure.getActive());
        }

        return procedureOutputPort.save(existing);
    }

    @Override
    public Procedure findById(UUID id) {
        return procedureOutputPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Procedimento não encontrado com o ID: " + id));
    }

    @Override
    public List<Procedure> findAllActive() {
        return procedureOutputPort.findByActiveTrue();
    }

    @Override
    public void delete(UUID id) {
        Procedure procedure = findById(id);
        procedureOutputPort.deleteById(procedure.getId());
    }
}