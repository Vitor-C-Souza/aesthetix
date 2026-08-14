package com.vitorcsouza.aesthetix.adapter.in.web.controller;

import com.vitorcsouza.aesthetix.adapter.in.web.dto.AuthRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.AuthResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.RegisterRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Register user")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Registered and returned JWT token"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Authenticate user")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Authenticated and returned JWT token"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody AuthRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}