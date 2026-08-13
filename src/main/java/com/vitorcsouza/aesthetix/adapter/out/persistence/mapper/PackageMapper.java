package com.vitorcsouza.aesthetix.adapter.out.persistence.mapper;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.PackageEntity;
import com.vitorcsouza.aesthetix.domain.model.Package;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PackageMapper {
    private final PatientMapper patientMapper;
    private final ProcedureMapper procedureMapper;

    public Package toDomain(PackageEntity entity) {
        if (entity == null) return null;

        return Package.builder()
                .id(entity.getId())
                .patient(patientMapper.toDomain(entity.getPatient()))
                .procedure(procedureMapper.toDomain(entity.getProcedure()))
                .totalSessions(entity.getTotalSessions())
                .usedSessions(entity.getUsedSessions())
                .totalPrice(entity.getTotalPrice())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public PackageEntity toEntity(Package domain) {
        if (domain == null) return null;

        return PackageEntity.builder()
                .id(domain.getId())
                .patient(patientMapper.toEntity(domain.getPatient()))
                .procedure(procedureMapper.toEntity(domain.getProcedure()))
                .totalSessions(domain.getTotalSessions())
                .usedSessions(domain.getUsedSessions())
                .totalPrice(domain.getTotalPrice())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
