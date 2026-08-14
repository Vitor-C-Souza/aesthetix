package com.vitorcsouza.aesthetix.adapter.in.web.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    // optional: ADMIN, PROFESSIONAL, RECEPTIONIST
    private String role;
}