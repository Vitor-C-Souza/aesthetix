package com.vitorcsouza.aesthetix.domain.port.out;

import com.vitorcsouza.aesthetix.domain.model.Role;

import java.util.Optional;
import java.util.Set;

public interface UserOutputPort {
    boolean existsByUsername(String username);

    Optional<UserData> findByUsername(String username);

    UserData save(String username, String encodedPassword, Set<Role> roles);

    record UserData(
            Long id,
            String username,
            String password,
            Set<Role> roles
    ) {
    }
}
