package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.EvolutionPhotoEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.mapper.EvolutionPhotoMapper;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataEvolutionPhotoRepository;
import com.vitorcsouza.aesthetix.domain.model.EvolutionPhoto;
import com.vitorcsouza.aesthetix.domain.model.PhotoType;
import com.vitorcsouza.aesthetix.domain.port.out.EvolutionPhotoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EvolutionPhotoPersistenceAdapter implements EvolutionPhotoOutputPort {
    private final SpringDataEvolutionPhotoRepository repository;
    private final EvolutionPhotoMapper mapper;

    @Override
    public EvolutionPhoto save(EvolutionPhoto evolutionPhoto) {
        EvolutionPhotoEntity entity = mapper.toEntity(evolutionPhoto);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<EvolutionPhoto> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<EvolutionPhoto> findByPatientIdOrderByCreatedAtDesc(UUID patientId) {
        return repository.findByPatientIdOrderByCreatedAtDesc(patientId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<EvolutionPhoto> findByAppointmentIdOrderByCreatedAtDesc(UUID appointmentId) {
        return repository.findByAppointmentIdOrderByCreatedAtDesc(appointmentId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<EvolutionPhoto> findByPatientIdAndPhotoTypeOrderByCreatedAtDesc(UUID patientId, PhotoType photoType) {
        return repository.findByPatientIdAndPhotoTypeOrderByCreatedAtDesc(patientId, photoType).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
