package com.userservice.userservice.auth.domain;

import com.userservice.userservice.auth.dto.AuthenticationRequest;
import com.userservice.userservice.auth.dto.AuthenticationResponse;
import com.userservice.userservice.model.Especialista;
import com.userservice.userservice.model.Paciente;
import com.userservice.userservice.model.Usuario;
import com.userservice.userservice.repository.EspecialistaRepository;
import com.userservice.userservice.repository.PacienteRepository;
import com.userservice.userservice.repository.PersonaRepository;
import com.userservice.userservice.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private EspecialistaRepository specialistRepository;

    @Autowired
    private PacienteRepository patientRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EspecialistaRepository especialistaRepository;


    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getCorreo(), authRequest.getPassword());

        Usuario usuario;
        try {
            authenticationManager.authenticate(authToken);
            usuario = userRepository.findByCorreo(authRequest.getCorreo()).orElse(null);
        } catch (Exception e) {
            return new AuthenticationResponse(null);
        }

        String jwt = jwtService.generateToken(usuario, generateExtraClaims(usuario));

        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(Usuario user) {
        String id = "";
        Integer id_paciente;
        boolean isEspecialista =false;
        Paciente paciente = pacienteRepository.findFirstByUsuarioIdUsuario(user.getIdUsuario()).orElse(null);
        if(paciente != null) {
            id_paciente = paciente.getIdPaciente();
        }else{
            id_paciente = null;
        }
        Especialista especialista = especialistaRepository.findFirstByUsuarioIdUsuario(user.getIdUsuario()).orElse(null);
        if(especialista !=null){
            isEspecialista = true;
        }

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("correo", user.getCorreo());
        extraClaims.put("documento", user.getPersona().getDocumento());
        extraClaims.put("id_usuario", user.getIdUsuario());
        extraClaims.put("id_paciente",id_paciente);
        extraClaims.put("isEspecialista",isEspecialista);
        return extraClaims;
    }
}
