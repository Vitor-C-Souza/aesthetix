package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.EvolutionPhotoRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.EvolutionPhotoResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.EvolutionPhotoWebMapper;
import com.vitorcsouza.aesthetix.domain.model.EvolutionPhoto;
import com.vitorcsouza.aesthetix.domain.port.in.EvolutionPhotoInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/evolution-photos")
@io.swagger.v3.oas.annotations.tags.Tag(name = "EvolutionPhoto")
@RequiredArgsConstructor
public class EvolutionPhotoController {

    private final EvolutionPhotoInputPort photoInputPort;
    private final EvolutionPhotoWebMapper webMapper;

    @PostMapping
    public ResponseEntity<EvolutionPhotoResponseDTO> create(@RequestBody @Valid EvolutionPhotoRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        EvolutionPhoto photo = webMapper.toDomain(requestDTO);
        EvolutionPhoto created = photoInputPort.create(photo);
        URI uri = uriBuilder.path("api/v1/evolution-photos/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvolutionPhotoResponseDTO> findById(@PathVariable UUID id) {
        EvolutionPhoto photo = photoInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(photo));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<EvolutionPhotoResponseDTO>> findByPatientId(@PathVariable UUID patientId) {
        List<EvolutionPhoto> list = photoInputPort.findByPatientId(patientId);
        List<EvolutionPhotoResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<EvolutionPhotoResponseDTO>> findByAppointmentId(@PathVariable UUID appointmentId) {
        List<EvolutionPhoto> list = photoInputPort.findByAppointmentId(appointmentId);
        List<EvolutionPhotoResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvolutionPhotoResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid EvolutionPhotoRequestDTO requestDTO) {
        EvolutionPhoto photo = webMapper.toDomain(requestDTO);
        EvolutionPhoto updated = photoInputPort.update(id, photo);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        photoInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
