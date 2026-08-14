package com.vitorcsouza.aesthetix.config;

import com.vitorcsouza.aesthetix.adapter.out.security.JwtAuthEntryPoint;
import com.vitorcsouza.aesthetix.adapter.out.security.JwtAuthenticationFilter;
import com.vitorcsouza.aesthetix.adapter.out.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    public SecurityConfig(JwtUtil jwtUtil, JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.jwtUtil = jwtUtil;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtUtil);

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthEntryPoint)
                        .accessDeniedHandler(jwtAuthEntryPoint)
                )
                .authorizeHttpRequests(auth -> auth
                        // public auth endpoints
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // Swagger/OpenAPI and static resources - allow public access for UI
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/favicon.ico",
                                "/webjars/**",
                                "/swagger-resources/**"
                        ).permitAll()

                        // Allow read access to API resources for authenticated roles
                        .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN", "PROFESSIONAL", "RECEPTIONIST")

                        // Professionals management: only ADMIN can create/update/delete
                        .requestMatchers(HttpMethod.POST, "/api/v1/professionals/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/professionals/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/professionals/**").hasRole("ADMIN")

                        // Patients: created/updated by RECEPTIONIST or ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/v1/patients/**").hasAnyRole("ADMIN", "RECEPTIONIST")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/patients/**").hasAnyRole("ADMIN", "RECEPTIONIST")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/patients/**").hasRole("ADMIN")

                        // Appointments: created/updated by RECEPTIONIST, PROFESSIONAL or ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/v1/appointments/**").hasAnyRole("ADMIN", "RECEPTIONIST", "PROFESSIONAL")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/appointments/**").hasAnyRole("ADMIN", "RECEPTIONIST", "PROFESSIONAL")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/appointments/**").hasAnyRole("ADMIN", "RECEPTIONIST", "PROFESSIONAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/appointments/**").hasAnyRole("ADMIN", "RECEPTIONIST")

                        // Default: any other request requires ADMIN
                        .anyRequest().hasRole("ADMIN")
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}