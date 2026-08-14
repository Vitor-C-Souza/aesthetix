package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.exception.ResourceNotFoundException;
import com.vitorcsouza.aesthetix.domain.model.Equipment;
import com.vitorcsouza.aesthetix.domain.port.in.EquipmentInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.EquipmentOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EquipmentService implements EquipmentInputPort {

    private final EquipmentOutputPort equipmentOutputPort;

    @Override
    public Equipment create(Equipment equipment) {
        return equipmentOutputPort.save(equipment);
    }

    @Override
    public Equipment update(UUID id, Equipment updatedEquipment) {
        Equipment existing = findById(id);

        existing.setName(updatedEquipment.getName());
        existing.setSerialNumber(updatedEquipment.getSerialNumber());

        if (updatedEquipment.getActive() != null) {
            existing.setActive(updatedEquipment.getActive());
        }

        return equipmentOutputPort.save(existing);
    }

    @Override
    public Equipment findById(UUID id) {
        return equipmentOutputPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com o ID: " + id));
    }

    @Override
    public List<Equipment> findAllActive() {
        return equipmentOutputPort.findByActiveTrue();
    }

    @Override
    public void delete(UUID id) {
        Equipment equipment = findById(id);
        equipmentOutputPort.deleteById(equipment.getId());
    }
}
