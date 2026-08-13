package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.AppointmentEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.AppointmentMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataAppointmentRepository;
import com.vitorcsouza.aesthetix.domain.model.Appointment;
import com.vitorcsouza.aesthetix.domain.model.AppointmentStatus;
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
        AppointmentEntity entity = mapper.toEntity(appointment);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Appointment> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Appointment> findByPatientIdOrderByStartTimeDesc(UUID patientId) {
        return repository.findByPatientIdOrderByStartTimeDesc(patientId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Appointment> findByProfessionalIdAndStartTimeBetweenOrderByStartTimeAsc(
            UUID professionalId, LocalDateTime start, LocalDateTime end
    ) {
        return repository.findByProfessionalIdAndStartTimeBetweenOrderByStartTimeAsc(professionalId, start, end).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Appointment> findByStatusAndStartTimeBetween(
            AppointmentStatus status, LocalDateTime start, LocalDateTime end
    ) {
        return repository.findByStatusAndStartTimeBetween(status, start, end).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsOverlappingAppointment(
            UUID professionalId, LocalDateTime startTime, LocalDateTime endTime, UUID appointmentId
    ) {
        return repository.existsOverlappingAppointment(professionalId, startTime, endTime, appointmentId);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
