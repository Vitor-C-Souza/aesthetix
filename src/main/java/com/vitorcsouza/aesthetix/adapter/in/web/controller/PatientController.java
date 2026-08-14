package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.PatientRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.PatientResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.PatientWebMapper;
import com.vitorcsouza.aesthetix.domain.DomainPage;
import com.vitorcsouza.aesthetix.domain.DomainPageRequest;
import com.vitorcsouza.aesthetix.domain.model.Patient;
import com.vitorcsouza.aesthetix.domain.port.in.PatientInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Patient")
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class PatientController {

    private final PatientInputPort patientInputPort;
    private final PatientWebMapper webMapper;

    @io.swagger.v3.oas.annotations.Operation(summary = "Create patient")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@RequestBody @Valid PatientRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Patient patient = webMapper.toDomain(requestDTO);
        Patient created = patientInputPort.create(patient);
        URI uri = uriBuilder.path("api/v1/patients/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Get patient by id")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> findById(@PathVariable UUID id) {
        Patient patient = patientInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(patient));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Search patients by name (paged)")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> findByName(
            @RequestParam(defaultValue = "") String name,
            Pageable pageable) {
        DomainPageRequest pageRequest = new DomainPageRequest(pageable.getPageNumber(), pageable.getPageSize());
        DomainPage<Patient> patients = patientInputPort.findByName(name, pageRequest);
        Page<PatientResponseDTO> dtoPage = new PageImpl<>(patients.getContent().stream().map(webMapper::toResponse).toList(), pageable, patients.getTotalElements());
        return ResponseEntity.ok(dtoPage);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Update patient")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid PatientRequestDTO requestDTO) {
        Patient patient = webMapper.toDomain(requestDTO);
        Patient updated = patientInputPort.update(id, patient);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Delete patient")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "No Content"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        patientInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
