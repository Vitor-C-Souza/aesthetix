package com.vitorcsouza.aesthetix.adapter.in.web.exception;

public record FieldErrorDTO(
        String field,
        String message
) {
}
