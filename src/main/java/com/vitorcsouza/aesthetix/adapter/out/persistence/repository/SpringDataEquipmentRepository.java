package com.vitorcsouza.aesthetix.adapter.out.persistence.repository;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataEquipmentRepository extends JpaRepository<EquipmentEntity, UUID> {
    List<EquipmentEntity> findByActiveTrue();

    Optional<EquipmentEntity> findBySerialNumber(String serialNumber);
}
