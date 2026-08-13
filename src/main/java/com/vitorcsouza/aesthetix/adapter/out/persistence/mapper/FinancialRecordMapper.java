package com.vitorcsouza.aesthetix.adapter.out.persistence.mapper;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.FinancialRecordEntity;
import com.vitorcsouza.aesthetix.domain.model.FinancialRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinancialRecordMapper {
    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;
    private final PackageMapper packageMapper;
    private final ProfessionalMapper professionalMapper;

    public FinancialRecord toDomain(FinancialRecordEntity entity) {
        if (entity == null) return null;

        FinancialRecord domain = new FinancialRecord();
        domain.setId(entity.getId());
        domain.setPatient(patientMapper.toDomain(entity.getPatient()));
        domain.setAppointment(appointmentMapper.toDomain(entity.getAppointment()));
        domain.setSessionPackage(packageMapper.toDomain(entity.getSessionPackage()));
        domain.setProfessional(professionalMapper.toDomain(entity.getProfessional()));
        domain.setAmount(entity.getAmount());
        domain.setCommissionAmount(entity.getCommissionAmount());
        domain.setPaymentMethod(entity.getPaymentMethod());
        domain.setStatus(entity.getStatus());
        domain.setPaidAt(entity.getPaidAt());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    public FinancialRecordEntity toEntity(FinancialRecord domain) {
        if (domain == null) return null;

        FinancialRecordEntity entity = new FinancialRecordEntity();
        entity.setId(domain.getId());
        entity.setPatient(patientMapper.toEntity(domain.getPatient()));
        entity.setAppointment(appointmentMapper.toEntity(domain.getAppointment()));
        entity.setSessionPackage(packageMapper.toEntity(domain.getSessionPackage()));
        entity.setProfessional(professionalMapper.toEntity(domain.getProfessional()));
        entity.setAmount(domain.getAmount());
        entity.setCommissionAmount(domain.getCommissionAmount());
        entity.setPaymentMethod(domain.getPaymentMethod());
        entity.setStatus(domain.getStatus());
        entity.setPaidAt(domain.getPaidAt());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }
}
