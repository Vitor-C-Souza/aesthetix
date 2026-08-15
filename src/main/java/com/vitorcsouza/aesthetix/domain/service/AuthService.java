package com.vitorcsouza.aesthetix.domain.service;

import com.vitorcsouza.aesthetix.domain.exception.BusinessException;
import com.vitorcsouza.aesthetix.domain.model.AuthResult;
import com.vitorcsouza.aesthetix.domain.model.Role;
import com.vitorcsouza.aesthetix.domain.port.in.AuthInputPort;
import com.vitorcsouza.aesthetix.domain.port.out.PasswordEncoderPort;
import com.vitorcsouza.aesthetix.domain.port.out.TokenOutputPort;
import com.vitorcsouza.aesthetix.domain.port.out.UserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthInputPort {

    private final UserOutputPort userOutputPort;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenOutputPort tokenOutputPort;


    @Override
    public AuthResult register(String username, String password, String roleName) {
        if (userOutputPort.existsByUsername(username)) {
            throw new BusinessException("Username already exists");
        }

        Role role = Role.RECEPTIONIST;

        if (roleName != null) {
            try {
                role = Role.valueOf(roleName);
            } catch (IllegalArgumentException ignored) {

            }
        }

        String encodedPassword = passwordEncoder.encode(password);

        UserOutputPort.UserData user = userOutputPort.save(username, encodedPassword, Set.of(role));

        String token = tokenOutputPort.generateToken(
                user.username(),
                user.roles()
        );

        return new AuthResult(user.username(), token);
    }

    @Override
    public AuthResult login(String username, String password) {
        UserOutputPort.UserData user = userOutputPort.findByUsername(username).orElseThrow(() -> new BusinessException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.password())) {
            throw new BusinessException("Invalid credentials");
        }

        String token = tokenOutputPort.generateToken(
                user.username(),
                user.roles()
        );

        return new AuthResult(user.username(), token);
    }
}
