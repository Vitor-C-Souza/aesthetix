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
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AnamnesisController {

    private final AnamnesisInputPort anamnesisInputPort;
    private final AnamnesisWebMapper webMapper;

    @io.swagger.v3.oas.annotations.Operation(summary = "Create anamnesis")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<AnamnesisResponseDTO> create(@RequestBody @Valid AnamnesisRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Anamnesis anamnesis = webMapper.toDomain(requestDTO);
        Anamnesis created = anamnesisInputPort.create(anamnesis);
        URI uri = uriBuilder.path("/api/v1/anamneses/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Get anamnesis by id")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnamnesisResponseDTO> findById(@PathVariable UUID id) {
        Anamnesis anamnesis = anamnesisInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(anamnesis));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "List anamneses by patient")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AnamnesisResponseDTO>> findByPatientId(@PathVariable UUID patientId) {
        List<Anamnesis> list = anamnesisInputPort.findByPatientId(patientId);
        List<AnamnesisResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Update anamnesis")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnamnesisResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid AnamnesisRequestDTO requestDTO) {
        Anamnesis anamnesis = webMapper.toDomain(requestDTO);
        Anamnesis updated = anamnesisInputPort.update(id, anamnesis);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Delete anamnesis")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "No Content"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        anamnesisInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
