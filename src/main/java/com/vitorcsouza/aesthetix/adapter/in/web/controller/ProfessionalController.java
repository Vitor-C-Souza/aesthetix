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
@io.swagger.v3.oas.annotations.tags.Tag(name = "Professional")
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ProfessionalController {

    private final ProfessionalInputPort professionalInputPort;
    private final ProfessionalWebMapper webMapper;

    @io.swagger.v3.oas.annotations.Operation(summary = "Create professional")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<ProfessionalResponseDTO> create(@RequestBody @Valid ProfessionalRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Professional professional = webMapper.toDomain(requestDTO);
        Professional created = professionalInputPort.create(professional);
        URI uri = uriBuilder.path("/api/v1/professionals/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Get professional by id")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> findById(@PathVariable UUID id) {
        Professional professional = professionalInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(professional));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "List professionals")
        @io.swagger.v3.oas.annotations.responses.ApiResponses({
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
        })
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

    @io.swagger.v3.oas.annotations.Operation(summary = "Update professional")
        @io.swagger.v3.oas.annotations.responses.ApiResponses({
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
        })
        @PutMapping("/{id}")
        public ResponseEntity<ProfessionalResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid ProfessionalRequestDTO requestDTO) {
        Professional professional = webMapper.toDomain(requestDTO);
        Professional updated = professionalInputPort.update(id, professional);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Delete professional")
        @io.swagger.v3.oas.annotations.responses.ApiResponses({
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "No Content"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable UUID id) {
        professionalInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
