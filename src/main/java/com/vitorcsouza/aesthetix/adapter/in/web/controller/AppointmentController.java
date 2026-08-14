package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.AppointmentRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.AppointmentResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.AppointmentWebMapper;
import com.vitorcsouza.aesthetix.domain.model.Appointment;
import com.vitorcsouza.aesthetix.domain.model.AppointmentStatus;
import com.vitorcsouza.aesthetix.domain.port.in.AppointmentInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appointments")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentInputPort appointmentInputPort;
    private final AppointmentWebMapper webMapper;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody @Valid AppointmentRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        Appointment appointment = webMapper.toDomain(requestDTO);
        Appointment created = appointmentInputPort.create(appointment);
        URI uri = uriBuilder.path("/api/v1/appointments/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(webMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable UUID id) {
        Appointment appointment = appointmentInputPort.findById(id);
        return ResponseEntity.ok(webMapper.toResponse(appointment));
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findByProfessionalAndPeriod(
            @PathVariable UUID professionalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<Appointment> list = appointmentInputPort.findByProfessionalAndPeriod(professionalId, start, end);
        List<AppointmentResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> findByPatient(@PathVariable UUID patientId) {
        List<Appointment> list = appointmentInputPort.findByPatient(patientId);
        List<AppointmentResponseDTO> response = list.stream()
                .map(webMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid AppointmentRequestDTO requestDTO) {
        Appointment appointment = webMapper.toDomain(requestDTO);
        Appointment updated = appointmentInputPort.update(id, appointment);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentResponseDTO> updateStatus(
            @PathVariable UUID id,
            @RequestParam AppointmentStatus status) {
        Appointment updated = appointmentInputPort.updateStatus(id, status);
        return ResponseEntity.ok(webMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable UUID id) {
        appointmentInputPort.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
