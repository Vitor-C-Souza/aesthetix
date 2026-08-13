package com.vitorcsouza.aesthetix.adapter.out.persistence.mapper;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.ProcedureEntity;
import com.vitorcsouza.aesthetix.domain.model.Procedure;
import org.springframework.stereotype.Component;

@Component
public class ProcedureMapper {
    public Procedure toDomain(ProcedureEntity entity) {
        if (entity == null) return null;

        return Procedure.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .durationInMinutes(entity.getDurationInMinutes())
                .salePrice(entity.getSalePrice())
                .requireEquipment(entity.getRequireEquipment())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public ProcedureEntity toEntity(Procedure domain) {
        if (domain == null) return null;

        return ProcedureEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .durationInMinutes(domain.getDurationInMinutes())
                .salePrice(domain.getSalePrice())
                .requireEquipment(domain.getRequireEquipment())
                .active(domain.getActive())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
