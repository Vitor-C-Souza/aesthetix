package com.vitorcsouza.aesthetix.adapter.in.web.exception;

import java.time.Instant;
import java.util.List;

public record ValidationError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path,
        List<FieldErrorDTO> errors
) {
}
