package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.model.*;
import com.vitorcsouza.aesthetix.domain.port.in.FinancialRecordInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.AppointmentOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.FinancialRecordOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PatientOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.ProfessionalOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinancialRecordService implements FinancialRecordInputPort {

    private final FinancialRecordOutputPort financialOutputPort;
    private final PatientOutputPort patientOutputPort;
    private final AppointmentOutputPort appointmentOutputPort;
    private final ProfessionalOutputPort professionalOutputPort;

    @Override
    public FinancialRecord create(FinancialRecord record) {
        Patient patient = patientOutputPort.findById(record.getPatient().getId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado."));
        record.setPatient(patient);

        if (record.getAppointment() != null && record.getAppointment().getId() != null) {
            Appointment appointment = appointmentOutputPort.findById(record.getAppointment().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado."));
            record.setAppointment(appointment);
        }

        if (record.getProfessional() != null && record.getProfessional().getId() != null) {
            Professional professional = professionalOutputPort.findById(record.getProfessional().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado."));
            record.setProfessional(professional);
        }

        if (PaymentStatus.PAID.equals(record.getStatus()) && record.getPaidAt() == null) {
            record.setPaidAt(LocalDateTime.now());
        }

        return financialOutputPort.save(record);
    }

    @Override
    public FinancialRecord update(UUID id, FinancialRecord updatedRecord) {
        FinancialRecord existing = findById(id);

        existing.setAmount(updatedRecord.getAmount());
        existing.setCommissionAmount(updatedRecord.getCommissionAmount());
        existing.setPaymentMethod(updatedRecord.getPaymentMethod());
        existing.setStatus(updatedRecord.getStatus());

        if (PaymentStatus.PAID.equals(updatedRecord.getStatus()) && existing.getPaidAt() == null) {
            existing.setPaidAt(LocalDateTime.now());
        } else {
            existing.setPaidAt(updatedRecord.getPaidAt());
        }

        return financialOutputPort.save(existing);
    }

    @Override
    public FinancialRecord findById(UUID id) {
        return financialOutputPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registro financeiro não encontrado com o ID: " + id));
    }

    @Override
    public List<FinancialRecord> findByPatientId(UUID patientId) {
        return financialOutputPort.findByPatientId(patientId);
    }

    @Override
    public List<FinancialRecord> findByStatus(PaymentStatus status) {
        return financialOutputPort.findByStatus(status);
    }

    @Override
    public void delete(UUID id) {
        FinancialRecord record = findById(id);
        financialOutputPort.deleteById(record.getId());
    }
}
