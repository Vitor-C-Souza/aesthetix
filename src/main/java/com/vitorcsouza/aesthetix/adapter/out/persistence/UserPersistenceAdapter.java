package com.vitorcsouza.aesthetix.adapter.out.persistence;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.UserEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataUserRepository;
import com.vitorcsouza.aesthetix.domain.model.Role;
import com.vitorcsouza.aesthetix.domain.port.out.UserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {

    private final SpringDataUserRepository userRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<UserData> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::toUserData);
    }

    @Override
    public UserData save(String username, String encodedPassword, Set<Role> roles) {
        UserEntity entity = new UserEntity();

        entity.setUsername(username);
        entity.setPassword(encodedPassword);
        entity.setRoles(roles);

        UserEntity saved = userRepository.save(entity);

        return toUserData(saved);
    }

    private UserData toUserData(UserEntity entity) {
        return new UserData(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRoles()
        );
    }
}
