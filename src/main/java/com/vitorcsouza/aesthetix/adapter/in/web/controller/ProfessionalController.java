package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.ProfessionalRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.ProfessionalResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.ProfessionalWebMapper;
import com.vitorcsouza.aesthetix.domain.model.Professional;
import com.vitorcsouza.aesthetix.domain.port.in.ProfessionalInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/professionals")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalInputPort professionalInputPort;
    private final ProfessionalWebMapper webMapper;

    @PostMapping
    public ResponseEntity<ProfessionalResponseDTO> create(@RequestBody @Valid ProfessionalRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Professional professional = webMapper.toDomain(requestDTO);
        Professional created = professionalInputPort.create(professional);
        URI uri = uriBuilder.path("/api/v1/professionals/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> findById(@PathVariable UUID id) {
        Professional professional = professionalInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(professional));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalResponseDTO>> findAll(
            @RequestParam(required = false) String specialty) {

        List<Professional> list = (specialty != null && !specialty.isBlank())
                ? professionalInputPort.findBySpecialty(specialty)
                : professionalInputPort.findAllActive();

        List<ProfessionalResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid ProfessionalRequestDTO requestDTO) {
        Professional professional = webMapper.toDomain(requestDTO);
        Professional updated = professionalInputPort.update(id, professional);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        professionalInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
