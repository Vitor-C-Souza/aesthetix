package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Role;

import java.util.Set;

public interface TokenPort {
    String generateToken(String username, Set<Role> roles);
}
