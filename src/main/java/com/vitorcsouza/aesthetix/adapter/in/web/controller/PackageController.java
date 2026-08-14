package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.PackageRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.PackageResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.PackageWebMapper;
import com.vitorcsouza.aesthetix.domain.model.Package;
import com.vitorcsouza.aesthetix.domain.port.in.PackageInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/packages")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Package")
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class PackageController {

    private final PackageInputPort packageInputPort;
    private final PackageWebMapper webMapper;

    @io.swagger.v3.oas.annotations.Operation(summary = "Create package")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<PackageResponseDTO> create(@RequestBody @Valid PackageRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Package sessionPackage = webMapper.toDomain(requestDTO);
        Package created = packageInputPort.create(sessionPackage);
        URI uri = uriBuilder.path("api/v1/packages/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Get package by id")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDTO> findById(@PathVariable UUID id) {
        Package sessionPackage = packageInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(sessionPackage));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "List packages by patient")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PackageResponseDTO>> findByPatientId(@PathVariable UUID patientId) {
        List<Package> list = packageInputPort.findByPatientId(patientId);
        List<PackageResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Consume package session")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PatchMapping("/{id}/consume")
    public ResponseEntity<PackageResponseDTO> consumeSession(@PathVariable UUID id) {
        Package updated = packageInputPort.consumeSession(id);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Update package")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PackageResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid PackageRequestDTO requestDTO) {
        Package sessionPackage = webMapper.toDomain(requestDTO);
        Package updated = packageInputPort.update(id, sessionPackage);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Delete package")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "No Content"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        packageInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
