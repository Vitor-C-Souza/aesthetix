package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.AnamnesisRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.AnamnesisResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.AnamnesisWebMapper;
import com.vitorcsouza.aesthetix.domain.model.Anamnesis;
import com.vitorcsouza.aesthetix.domain.port.in.AnamnesisInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/anamneses")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Anamnesis")
@RequiredArgsConstructor
public class AnamnesisController {

    private final AnamnesisInputPort anamnesisInputPort;
    private final AnamnesisWebMapper webMapper;

    @PostMapping
    public ResponseEntity<AnamnesisResponseDTO> create(@RequestBody @Valid AnamnesisRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Anamnesis anamnesis = webMapper.toDomain(requestDTO);
        Anamnesis created = anamnesisInputPort.create(anamnesis);
        URI uri = uriBuilder.path("/api/v1/anamneses/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnamnesisResponseDTO> findById(@PathVariable UUID id) {
        Anamnesis anamnesis = anamnesisInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(anamnesis));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AnamnesisResponseDTO>> findByPatientId(@PathVariable UUID patientId) {
        List<Anamnesis> list = anamnesisInputPort.findByPatientId(patientId);
        List<AnamnesisResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnamnesisResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid AnamnesisRequestDTO requestDTO) {
        Anamnesis anamnesis = webMapper.toDomain(requestDTO);
        Anamnesis updated = anamnesisInputPort.update(id, anamnesis);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        anamnesisInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
