package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Credentials for authentication")
public class AuthRequestDTO {
    @NotBlank
    @Schema(description = "Username of the user", example = "admin")
    private String username;
    @NotBlank
    @Schema(description = "Plain text password", example = "password123")
    private String password;
}