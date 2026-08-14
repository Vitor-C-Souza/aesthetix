package com.vitorcsouza.aesthetix.adapter.in.web.exception;

import java.time.Instant;
import java.util.List;

@io.swagger.v3.oas.annotations.media.Schema(description = "Validation error response", example = "{\"timestamp\":\"2026-08-13T21:00:00Z\",\"status\":422,\"error\":\"Erro de Validação\",\"message\":\"Um ou mais campos estão inválidos\",\"path\":\"/api/v1/resource\",\"errors\":[{\"field\":\"name\",\"message\":\"O nome é obrigatório\"}]}")
public record ValidationError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path,
        List<FieldErrorDTO> errors
) {
}
