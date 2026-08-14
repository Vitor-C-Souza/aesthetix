package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.Equipment;

import java.util.List;
import java.util.UUID;

public interface EquipmentInputPort {
    Equipment create(Equipment equipment);

    Equipment update(UUID id, Equipment equipment);

    Equipment findById(UUID id);

    List<Equipment> findAllActive();

    void delete(UUID id);
}
