package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.ProcedureRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.ProcedureResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.ProcedureWebMapper;
import com.vitorcsouza.aesthetix.domain.model.Procedure;
import com.vitorcsouza.aesthetix.domain.port.in.ProcedureInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/procedures")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Procedure")
@RequiredArgsConstructor
public class ProcedureController {
    private final ProcedureInputPort procedureInputPort;
    private final ProcedureWebMapper webMapper;

    @PostMapping
    public ResponseEntity<ProcedureResponseDTO> create(@RequestBody @Valid ProcedureRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Procedure procedure = webMapper.toDomain(requestDTO);
        Procedure created = procedureInputPort.create(procedure);
        URI uri = uriBuilder.path("/api/v1/procedures/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedureResponseDTO> findById(@PathVariable UUID id) {
        Procedure procedure = procedureInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(procedure));
    }

    @GetMapping
    public ResponseEntity<List<ProcedureResponseDTO>> findAll() {
        List<Procedure> list = procedureInputPort.findAllActive();
        List<ProcedureResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedureResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid ProcedureRequestDTO requestDTO) {
        Procedure procedure = webMapper.toDomain(requestDTO);
        Procedure updated = procedureInputPort.update(id, procedure);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        procedureInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
