package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@Schema(description = "Response containing JWT token")
public class AuthResponseDTO {
    @Schema(description = "JWT token to be used in Authorization header", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
}