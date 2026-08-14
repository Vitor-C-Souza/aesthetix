package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import com.vitorcsouza.aesthetix.domain.model.*;
import com.vitorcsouza.aesthetix.domain.model.Package;

import org.springframework.stereotype.Component;


import java.math.BigDecimal;

@Component
public class FinancialRecordWebMapper {
    public FinancialRecord toDomain(FinancialRecordRequestDTO dto) {
        if (dto == null) return null;

        return FinancialRecord.builder()
                .patient(Patient.builder().id(dto.patientId()).build())
                .appointment(dto.appointmentId() != null ? Appointment.builder().id(dto.appointmentId()).build() : null)
                .sessionPackage(dto.packageId() != null ? Package.builder().id(dto.packageId()).build() : null)
                .professional(dto.professionalId() != null ? Professional.builder().id(dto.professionalId()).build() : null)
                .amount(dto.amount())
                .commissionAmount(dto.commissionAmount() != null ? dto.commissionAmount() : BigDecimal.ZERO)
                .paymentMethod(dto.paymentMethod())
                .status(dto.status() != null ? dto.status() : PaymentStatus.PENDING)
                .paidAt(dto.paidAt())
                .build();
    }

    public FinancialRecordResponseDTO toResponse(FinancialRecord domain) {
        if (domain == null) return null;

        return new FinancialRecordResponseDTO(
                domain.getId(),
                domain.getPatient() != null ? domain.getPatient().getId() : null,
                domain.getPatient() != null ? domain.getPatient().getName() : null,
                domain.getAppointment() != null ? domain.getAppointment().getId() : null,
                domain.getSessionPackage() != null ? domain.getSessionPackage().getId() : null,
                domain.getProfessional() != null ? domain.getProfessional().getId() : null,
                domain.getProfessional() != null ? domain.getProfessional().getName() : null,
                domain.getAmount(),
                domain.getCommissionAmount(),
                domain.getPaymentMethod(),
                domain.getStatus(),
                domain.getPaidAt(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
