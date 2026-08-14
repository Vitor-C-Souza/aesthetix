package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Payload to register a new user")
public class RegisterRequestDTO {
    @NotBlank
    @Schema(description = "Username to register", example = "user1")
    private String username;
    @NotBlank
    @Schema(description = "Plain text password", example = "P@ssw0rd")
    private String password;
    // optional: ADMIN, PROFESSIONAL, RECEPTIONIST
    @Schema(description = "Role for the user (ADMIN, PROFESSIONAL, RECEPTIONIST)")
    private String role;
}