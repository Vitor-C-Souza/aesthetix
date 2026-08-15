package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.AuthRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.AuthResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.RegisterRequestDTO;
import com.vitorcsouza.aesthetix.domain.model.AuthResult;
import com.vitorcsouza.aesthetix.domain.port.in.AuthInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@io.swagger.v3.oas.annotations.tags.Tag(name = "Auth")
public class AuthController {

    private final AuthInputPort authInputPort;


    @io.swagger.v3.oas.annotations.Operation(summary = "Register user")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Registered and returned JWT token"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody RegisterRequestDTO dto) {

        AuthResult result = authInputPort.register(
                dto.getUsername(),
                dto.getPassword(),
                dto.getRole()
        );

        return ResponseEntity.ok(
                new AuthResponseDTO(result.token())
        );
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Authenticate user")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Authenticated and returned JWT token"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody AuthRequestDTO dto
    ) {
        AuthResult result = authInputPort.login(
                dto.getUsername(),
                dto.getPassword()
        );

        return ResponseEntity.ok(
                new AuthResponseDTO(result.token())
        );
    }
}