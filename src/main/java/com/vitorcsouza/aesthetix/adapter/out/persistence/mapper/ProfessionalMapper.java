package com.vitorcsouza.aesthetix.adapter.out.persistence.mapper;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.ProfessionalEntity;
import com.vitorcsouza.aesthetix.domain.model.Professional;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalMapper {
    public Professional toDomain(ProfessionalEntity entity) {
        if (entity == null) return null;

        return Professional.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .phone(entity.getPhone())
                .specialty(entity.getSpecialty())
                .commissionRate(entity.getCommissionRate())
                .colorCode(entity.getColorCode())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public ProfessionalEntity toEntity(Professional domain) {
        if (domain == null) return null;

        return ProfessionalEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .cpf(domain.getCpf())
                .phone(domain.getPhone())
                .specialty(domain.getSpecialty())
                .commissionRate(domain.getCommissionRate())
                .colorCode(domain.getColorCode())
                .active(domain.getActive())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
