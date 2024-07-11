package com.userservice.userservice.controller;

import com.userservice.userservice.auth.domain.AuthenticationService;
import com.userservice.userservice.auth.domain.JwtService;
import com.userservice.userservice.auth.dto.AuthenticationRequest;
import com.userservice.userservice.auth.dto.AuthenticationResponse;
import com.userservice.userservice.dto.PersonaRequest;
import com.userservice.userservice.dto.PersonaResponse;
import com.userservice.userservice.dto.UsuarioRequest;
import com.userservice.userservice.dto.UsuarioResponse;
import com.userservice.userservice.model.Usuario;
import com.userservice.userservice.repository.UsuarioRepository;
import com.userservice.userservice.service.UsuarioService;
import com.userservice.userservice.utilz.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/isTokenExpired")
    public ResponseEntity<?> secureResource(@RequestHeader(name = "Authorization") String authorizationHeader) {
        Map<String, Object> data = new HashMap<>();
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String jwt = authorizationHeader.substring(7);
                return jwtService.is_token_Expired(jwt);
            }
        }catch (Exception e) {
            data.put("message", "Error al verificar el token");
            data.put("error",e.getMessage());
            data.put("status", 500);
        }
        return ResponseEntity.status(500).body(data);
    }

    @GetMapping("/validator")
    public  ResponseEntity<?>  validator_email(@RequestParam String correo){
        return  usuarioService.validator_email(correo);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponse> get_usuarios(){
        return usuarioService.getAllUsuarios();
    }
    @GetMapping("/get/{documento}")
    public ResponseEntity<Object>get_usuario(@PathVariable String documento) {
        return usuarioService.get_usuario(documento);
    }

    @PostMapping("/insert")
    public UsuarioResponse insertPersona(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.insert(usuarioRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthenticationRequest authRequest) {
        Usuario usuario = userRepository.findByCorreo(authRequest.getCorreo()).orElse(null);
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
}

