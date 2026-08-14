package com.vitorcsouza.aesthetix.adapter.in.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitorcsouza.aesthetix.adapter.in.web.exception.StandardError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        StandardError err = new StandardError(Instant.now(), HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized", authException.getMessage(), request.getRequestURI());
        mapper.writeValue(response.getOutputStream(), err);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        StandardError err = new StandardError(Instant.now(), HttpServletResponse.SC_FORBIDDEN, "Access Denied", accessDeniedException.getMessage(), request.getRequestURI());
        mapper.writeValue(response.getOutputStream(), err);
    }
}