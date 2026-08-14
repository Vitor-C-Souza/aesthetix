package com.vitorcsouza.aesthetix.adapter.in.web.security;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.UserEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataUserRepository;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.AuthRequestDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.AuthResponseDTO;
import com.vitorcsouza.aesthetix.adapter.in.web.dto.RegisterRequestDTO;
import com.vitorcsouza.aesthetix.domain.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final SpringDataUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(SpringDataUserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new com.vitorcsouza.aesthetix.domain.exception.BusinessException("Username already exists");
        }

        Role role = Role.RECEPTIONIST;
        if (dto.getRole() != null) {
            try {
                role = Role.valueOf(dto.getRole());
            } catch (Exception ex) {
                // ignore and use default
            }
        }

        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Set.of(role));

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(AuthRequestDTO dto) {
        Optional<UserEntity> opt = userRepository.findByUsername(dto.getUsername());
        if (opt.isEmpty()) throw new com.vitorcsouza.aesthetix.domain.exception.BusinessException("Invalid credentials");

        UserEntity user = opt.get();
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new com.vitorcsouza.aesthetix.domain.exception.BusinessException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
        return new AuthResponseDTO(token);
    }
}