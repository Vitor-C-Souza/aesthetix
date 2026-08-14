package com.vitorcsouza.aesthetix.adapter.in.web.exception;

@io.swagger.v3.oas.annotations.media.Schema(description = "Field-level validation error", example = "{\"field\":\"name\",\"message\":\"O nome é obrigatório\"}")
public record FieldErrorDTO(
        String field,
        String message
) {
}
