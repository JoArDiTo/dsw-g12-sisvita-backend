package com.userservice.userservice.config.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(HttpMethod.POST, "/usuarios/login").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/usuarios/validator").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/usuarios/get/{documento}").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/usuarios/insert").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/personas/get/{documento}").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/personas/insert").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/ubigeos/get").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/pacientes/get/{id_usuario}").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/pacientes/insert").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/especialistas/get/{id_usuario}").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/especialistas/insert").permitAll();

                    authConfig.anyRequest().authenticated();
                });
        return http.build();
    }
}
