package com.example.msvcusers_roles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MsvcConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                // API REST + JWT = no necesitamos CSRF
                .csrf(AbstractHttpConfigurer::disable)

                // El microservicio no mantiene sesiones.
                // Cada petición protegida debe traer su JWT.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(authorize -> authorize

                        /*
                         * =====================================================
                         * ENDPOINT PÚBLICO PARA EL AUTH SERVER
                         * =====================================================
                         *
                         * Este endpoint valida username/password.
                         *
                         * NO puede exigir JWT porque precisamente se utiliza
                         * antes de que el usuario tenga un JWT.
                         */
                        .requestMatchers(
                                HttpMethod.POST,
                                "/user/auth/user"
                        ).permitAll()

                        /*
                         * Health check
                         */
                        .requestMatchers(
                                "/actuator/health",
                                "/error"
                        ).permitAll()

                        /*
                         * Todo lo demás requiere JWT válido
                         */
                        .anyRequest().authenticated()
                )

                /*
                 * Convierte este microservicio en OAuth2 Resource Server.
                 */
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults())
                );

        return http.build();
    }
}
