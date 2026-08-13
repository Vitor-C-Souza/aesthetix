package com.vitorcsouza.aesthetix.adapter.out.persistence.mapper;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.EquipmentEntity;
import com.vitorcsouza.aesthetix.domain.model.Equipment;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {
    public Equipment toDomain(EquipmentEntity entity) {
        if (entity == null) return null;

        return Equipment.builder()
                .id(entity.getId())
                .name(entity.getName())
                .serialNumber(entity.getSerialNumber())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public EquipmentEntity toEntity(Equipment domain) {
        if (domain == null) return null;

        return EquipmentEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .serialNumber(domain.getSerialNumber())
                .active(domain.getActive())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
