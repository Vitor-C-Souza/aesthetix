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
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentInputPort equipmentInputPort;
    private final EquipmentWebMapper webMapper;

    @PostMapping
    public ResponseEntity<EquipmentResponseDTO> create(@RequestBody @Valid EquipmentRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Equipment equipment = webMapper.toDomain(requestDTO);
        Equipment created = equipmentInputPort.create(equipment);
        URI uri = uriBuilder.path("/api/v1/equipments/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> findById(@PathVariable UUID id) {
        Equipment equipment = equipmentInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(equipment));
    }

    @GetMapping
    public ResponseEntity<List<EquipmentResponseDTO>> findAllActive() {
        List<Equipment> list = equipmentInputPort.findAllActive();
        List<EquipmentResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid EquipmentRequestDTO requestDTO) {
        Equipment equipment = webMapper.toDomain(requestDTO);
        Equipment updated = equipmentInputPort.update(id, equipment);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        equipmentInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
