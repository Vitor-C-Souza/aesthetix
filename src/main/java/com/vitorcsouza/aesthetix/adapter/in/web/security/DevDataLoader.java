package com.vitorcsouza.aesthetix.adapter.in.web.security;

import com.vitorcsouza.aesthetix.adapter.out.persistence.entity.UserEntity;
import com.vitorcsouza.aesthetix.adapter.out.persistence.repository.SpringDataUserRepository;
import com.vitorcsouza.aesthetix.domain.model.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Profile("dev")
public class DevDataLoader implements ApplicationRunner {

    private final SpringDataUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DevDataLoader(SpringDataUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Set.of(Role.ADMIN));
            userRepository.save(admin);
            System.out.println("[dev] Default admin user created: username=admin password=admin");
        }
    }
}