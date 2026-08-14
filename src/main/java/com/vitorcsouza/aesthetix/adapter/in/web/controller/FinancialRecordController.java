package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.FinancialRecordRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.FinancialRecordResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.FinancialRecordWebMapper;
import com.vitorcsouza.aesthetix.domain.model.FinancialRecord;
import com.vitorcsouza.aesthetix.domain.model.PaymentStatus;
import com.vitorcsouza.aesthetix.domain.port.in.FinancialRecordInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/financial-records")
@io.swagger.v3.oas.annotations.tags.Tag(name = "FinancialRecord")
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
public class FinancialRecordController {

    private final FinancialRecordInputPort financialInputPort;
    private final FinancialRecordWebMapper webMapper;

    @io.swagger.v3.oas.annotations.Operation(summary = "Create financial record")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<FinancialRecordResponseDTO> create(@RequestBody @Valid FinancialRecordRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        FinancialRecord record = webMapper.toDomain(requestDTO);
        FinancialRecord created = financialInputPort.create(record);
        URI uri = uriBuilder.path("/api/v1/financial-records/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Get financial record by id")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FinancialRecordResponseDTO> findById(@PathVariable UUID id) {
        FinancialRecord record = financialInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(record));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "List financial records by patient")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<FinancialRecordResponseDTO>> findByPatientId(@PathVariable UUID patientId) {
        List<FinancialRecord> list = financialInputPort.findByPatientId(patientId);
        List<FinancialRecordResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "List financial records by status")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<FinancialRecordResponseDTO>> findByStatus(@PathVariable PaymentStatus status) {
        List<FinancialRecord> list = financialInputPort.findByStatus(status);
        List<FinancialRecordResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Update financial record")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FinancialRecordResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid FinancialRecordRequestDTO requestDTO) {
        FinancialRecord record = webMapper.toDomain(requestDTO);
        FinancialRecord updated = financialInputPort.update(id, record);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Delete financial record")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "No Content"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        financialInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
