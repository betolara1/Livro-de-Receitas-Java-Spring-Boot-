package com.roberto.Livro_de_Receitas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // MANTER O CERTIFICADO DESATIVADO PRO POSTMAN ENVIAR AS REQUISIÇÕES
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/v3/api-docs/**",
                    "/v3/api-docs.yaml",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/webjars/**").permitAll() // LIBERAR ACESSO AOS ARQUIVOS DO SWAGGER
            .requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    
    // 1. QUEM CODIFICA A SENHA
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. AUTHENTICATIONMANAGER PRONTO DA CONFIGURAÇÃO DO SPRING
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}