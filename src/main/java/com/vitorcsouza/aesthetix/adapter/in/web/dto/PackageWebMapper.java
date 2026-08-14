package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.Package;
import com.vitorcsouza.aesthetix.domain.model.PackageStatus;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.model.Procedure;
import org.springframework.stereotype.Component;

@Component
public class PackageWebMapper {
    public Package toDomain(PackageRequestDTO dto) {
        if (dto == null) return null;

        return Package.builder()
                .patient(Patient.builder().id(dto.patientId()).build())
                .procedure(Procedure.builder().id(dto.procedureId()).build())
                .totalSessions(dto.totalSessions())
                .usedSessions(dto.usedSessions() != null ? dto.usedSessions() : 0)
                .totalPrice(dto.totalPrice())
                .status(dto.status() != null ? dto.status() : PackageStatus.ACTIVE)
                .build();
    }

    public PackageResponseDTO toResponse(Package domain) {
        if (domain == null) return null;

        return new PackageResponseDTO(
                domain.getId(),
                domain.getPatient() != null ? domain.getPatient().getId() : null,
                domain.getPatient() != null ? domain.getPatient().getName() : null,
                domain.getProcedure() != null ? domain.getProcedure().getId() : null,
                domain.getProcedure() != null ? domain.getProcedure().getName() : null,
                domain.getTotalSessions(),
                domain.getUsedSessions(),
                domain.getTotalPrice(),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
