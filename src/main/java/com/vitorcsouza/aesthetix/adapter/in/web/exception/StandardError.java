package com.vitorcsouza.aesthetix.adapter.in.web.exception;

import java.time.Instant;

@io.swagger.v3.oas.annotations.media.Schema(description = "Standard error response", example = "{\"timestamp\":\"2026-08-13T21:00:00Z\",\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Full message here\",\"path\":\"/api/v1/resource\"}")
public record StandardError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
