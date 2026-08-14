package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.EquipmentRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.EquipmentResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.EquipmentWebMapper;
import com.vitorcsouza.aesthetix.domain.model.Equipment;
import com.vitorcsouza.aesthetix.domain.port.in.EquipmentInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/equipments")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Equipment")
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentInputPort equipmentInputPort;
    private final EquipmentWebMapper webMapper;

    @io.swagger.v3.oas.annotations.Operation(summary = "Create equipment")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<EquipmentResponseDTO> create(@RequestBody @Valid EquipmentRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Equipment equipment = webMapper.toDomain(requestDTO);
        Equipment created = equipmentInputPort.create(equipment);
        URI uri = uriBuilder.path("/api/v1/equipments/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Get equipment by id")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> findById(@PathVariable UUID id) {
        Equipment equipment = equipmentInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(equipment));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "List equipment")
        @io.swagger.v3.oas.annotations.responses.ApiResponses({
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
        })
        @GetMapping
        public ResponseEntity<List<EquipmentResponseDTO>> findAllActive() {
        List<Equipment> list = equipmentInputPort.findAllActive();
        List<EquipmentResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Update equipment")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid EquipmentRequestDTO requestDTO) {
        Equipment equipment = webMapper.toDomain(requestDTO);
        Equipment updated = equipmentInputPort.update(id, equipment);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Delete equipment")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "No Content"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        equipmentInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
