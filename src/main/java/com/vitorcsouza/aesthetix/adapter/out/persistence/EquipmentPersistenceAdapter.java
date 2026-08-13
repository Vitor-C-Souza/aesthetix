package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.EquipmentEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.EquipmentMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataEquipmentRepository;
import com.vitorcsouza.aesthetix.domain.model.Equipment;
import com.vitorcsouza.aesthetix.domain.port.out.EquipmentOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EquipmentPersistenceAdapter implements EquipmentOutputPort {
    private final SpringDataEquipmentRepository repository;
    private final EquipmentMapper mapper;

    @Override
    public Equipment save(Equipment equipment) {
        EquipmentEntity entity = mapper.toEntity(equipment);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Equipment> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Equipment> findBySerialNumber(String serialNumber) {
        return repository.findBySerialNumber(serialNumber).map(mapper::toDomain);
    }

    @Override
    public List<Equipment> findByActiveTrue() {
        return repository.findByActiveTrue().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
