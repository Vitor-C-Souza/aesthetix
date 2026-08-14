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
@RequiredArgsConstructor
public class PackageController {

    private final PackageInputPort packageInputPort;
    private final PackageWebMapper webMapper;

    @PostMapping
    public ResponseEntity<PackageResponseDTO> create(@RequestBody @Valid PackageRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Package sessionPackage = webMapper.toDomain(requestDTO);
        Package created = packageInputPort.create(sessionPackage);
        URI uri = uriBuilder.path("api/v1/packages/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDTO> findById(@PathVariable UUID id) {
        Package sessionPackage = packageInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(sessionPackage));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PackageResponseDTO>> findByPatientId(@PathVariable UUID patientId) {
        List<Package> list = packageInputPort.findByPatientId(patientId);
        List<PackageResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/consume")
    public ResponseEntity<PackageResponseDTO> consumeSession(@PathVariable UUID id) {
        Package updated = packageInputPort.consumeSession(id);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid PackageRequestDTO requestDTO) {
        Package sessionPackage = webMapper.toDomain(requestDTO);
        Package updated = packageInputPort.update(id, sessionPackage);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        packageInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
