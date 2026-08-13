package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.AppointmentMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataAppointmentRepository;
import com.vitorcsouza.aesthetix.domain.model.Appointment;
import com.vitorcsouza.aesthetix.domain.port.out.AppointmentOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AppointmentPersistenceAdapter implements AppointmentOutputPort {
    private final SpringDataAppointmentRepository repository;
    private final AppointmentMapper mapper;

    @Override
    public Appointment save(Appointment appointment) {
        var entity = mapper.toEntity(appointment);
        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Appointment> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Appointment> findByProfessionalIdAndStartTimeBetween(UUID professionalId, LocalDateTime start, LocalDateTime end) {
        return repository.findByProfessionalIdAndStartTimeBetween(professionalId, start, end)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Appointment> findByPatientId(UUID patientId) {
        return repository.findByPatientId(patientId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
