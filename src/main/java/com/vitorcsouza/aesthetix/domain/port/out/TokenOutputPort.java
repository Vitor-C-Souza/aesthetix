package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Role;

import java.util.Collection;

public interface TokenOutputPort {
    String generateToken(String username, Collection<Role> roles);

    boolean validateToken(String token);

    String getUsernameFromToken(String token);

    Collection<Role> getRolesFromToken(String token);
}
