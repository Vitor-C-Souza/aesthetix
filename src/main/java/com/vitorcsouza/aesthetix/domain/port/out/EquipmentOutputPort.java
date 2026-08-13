package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Equipment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipmentOutputPort {
    Equipment save(Equipment equipment);

    Optional<Equipment> findById(UUID id);

    Optional<Equipment> findBySerialNumber(String serialNumber);

    List<Equipment> findByActiveTrue();

    void deleteById(UUID id);
}
