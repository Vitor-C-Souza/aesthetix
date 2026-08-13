package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.PatientRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.PatientResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.PatientWebMapper;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.port.in.PatientInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientInputPort patientInputPort;
    private final PatientWebMapper webMapper;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@RequestBody @Valid PatientRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Patient patient = webMapper.toDomain(requestDTO);
        Patient created = patientInputPort.create(patient);
        URI uri = uriBuilder.path("api/v1/patients/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> findById(@PathVariable UUID id) {
        Patient patient = patientInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> findByName(
            @RequestParam(defaultValue = "") String name,
            Pageable pageable) {
        Page<Patient> patients = patientInputPort.findByName(name, pageable);
        return ResponseEntity.ok(patients.map(webMapper::toResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid PatientRequestDTO requestDTO) {
        Patient patient = webMapper.toDomain(requestDTO);
        Patient updated = patientInputPort.update(id, patient);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        patientInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
