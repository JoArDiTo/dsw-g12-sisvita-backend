package com.userservice.userservice.service;

import com.userservice.userservice.dto.*;
import com.userservice.userservice.model.Paciente;
import com.userservice.userservice.model.Persona;
import com.userservice.userservice.model.Ubigeo;
import com.userservice.userservice.model.Usuario;
import com.userservice.userservice.repository.EspecialistaRepository;
import com.userservice.userservice.repository.PacienteRepository;
import com.userservice.userservice.repository.PersonaRepository;
import com.userservice.userservice.repository.UsuarioRepository;
import com.userservice.userservice.utilz.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public List<UsuarioResponse> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::mapToUsuarioResponse).toList();
    }

    public ResponseEntity<Object> get_usuario(String documento) {
        Usuario usuario = usuarioRepository.findFirstByPersonaDocumento(documento).orElse(null);
        if (usuario == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "Usuario no encontrado");
            data.put("status", 400);
            return ResponseEntity.status(400).body(data);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Usuario encontrada con éxito");
        data.put("status", 200);
        data.put("persona", this.mapToUsuarioResponse(usuario));

        return ResponseEntity.status(200).body(data);
    }

    public UsuarioResponse insert(UsuarioRequest usuarioRequest) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(usuarioRequest.getPassword());

        Persona persona = personaRepository.findById(usuarioRequest.getDocumento()).orElse(null);
        Usuario usuario= new Usuario();
        usuario.setCorreo(usuarioRequest.getCorreo());
        usuario.setPassword(encodedPassword);
        usuario.setPersona(persona);

        usuarioRepository.save(usuario);

        return mapToUsuarioResponse(usuario);
    }


    public ResponseEntity<?> validator_email(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo).orElse(null);
        Map<String, Object> data = new HashMap<>();

        if (usuario != null) {
            data.put("message", "Correo ya registrado");
            data.put("status", 400);
            return ResponseEntity.status(400)
                    .body(data);
        }
        data.put("message", "Correo disponible");
        data.put("status",200);
        return ResponseEntity.ok(data);
    }



    private UsuarioResponse mapToUsuarioResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .correo(usuario.getCorreo())
                .password(usuario.getPassword())
                .documento(usuario.getPersona().getDocumento())
                .persona(usuario.getPersona()).build();
    }


}
