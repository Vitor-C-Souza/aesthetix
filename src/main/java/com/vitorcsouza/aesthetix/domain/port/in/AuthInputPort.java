package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.AuthResult;

public interface AuthInputPort {
    AuthResult register(String username, String password, String role);

    AuthResult login(String username, String password);
}
