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
public class FinancialRecordController {

    private final FinancialRecordInputPort financialInputPort;
    private final FinancialRecordWebMapper webMapper;

    @PostMapping
    public ResponseEntity<FinancialRecordResponseDTO> create(@RequestBody @Valid FinancialRecordRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        FinancialRecord record = webMapper.toDomain(requestDTO);
        FinancialRecord created = financialInputPort.create(record);
        URI uri = uriBuilder.path("/api/v1/financial-records/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialRecordResponseDTO> findById(@PathVariable UUID id) {
        FinancialRecord record = financialInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(record));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<FinancialRecordResponseDTO>> findByPatientId(@PathVariable UUID patientId) {
        List<FinancialRecord> list = financialInputPort.findByPatientId(patientId);
        List<FinancialRecordResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<FinancialRecordResponseDTO>> findByStatus(@PathVariable PaymentStatus status) {
        List<FinancialRecord> list = financialInputPort.findByStatus(status);
        List<FinancialRecordResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialRecordResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid FinancialRecordRequestDTO requestDTO) {
        FinancialRecord record = webMapper.toDomain(requestDTO);
        FinancialRecord updated = financialInputPort.update(id, record);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        financialInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
