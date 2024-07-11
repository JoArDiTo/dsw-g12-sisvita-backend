package com.userservice.userservice.auth.web;

import com.userservice.userservice.auth.domain.AuthenticationService;
import com.userservice.userservice.auth.dto.AuthenticationRequest;
import com.userservice.userservice.auth.dto.AuthenticationResponse;
import com.userservice.userservice.model.Usuario;
import com.userservice.userservice.repository.UsuarioRepository;
import com.userservice.userservice.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsuarioRepository userRepository;

    @PreAuthorize("permitAll")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthenticationRequest authRequest) {
        Usuario usuario= userRepository.findByCorreo(authRequest.getCorreo()).orElse(null);
        Map<String, String> responseBody= new HashMap<>();

        if (usuario == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("User not found").build());
        }

        AuthenticationResponse response = authenticationService.login(authRequest);

        if (response.getJwt() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Password incorrect").build());
        }

        responseBody.put("jwt", response.getJwt());
        return ResponseEntity.ok(responseBody);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/public-access")
    public ResponseEntity<?> publicAccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "public access");
        return ResponseEntity.ok(response);
    }
}
