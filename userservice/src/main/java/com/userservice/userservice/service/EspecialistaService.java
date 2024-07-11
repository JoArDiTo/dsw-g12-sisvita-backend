package com.userservice.userservice.service;

import com.userservice.userservice.dto.EspecialistaRequest;
import com.userservice.userservice.dto.PacienteRequest;
import com.userservice.userservice.dto.EspecialistaResponse;
import com.userservice.userservice.model.Especialista;
import com.userservice.userservice.model.Paciente;
import com.userservice.userservice.repository.PacienteRepository;
import com.userservice.userservice.repository.UsuarioRepository;
import com.userservice.userservice.repository.EspecialistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EspecialistaService {

    private final EspecialistaRepository especialistaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<EspecialistaResponse> getEspecialistas() {
        List<Especialista> especialistas = especialistaRepository.findAll();
        return especialistas.stream().map(this::mapToEspecialistaResponse).toList();
    }

    public ResponseEntity<Object> getEspecialista(Integer id_usuario) {
        Especialista especialista = especialistaRepository.findById(id_usuario).orElse(null);

        if (especialista == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "Especialista no encontrado");
            data.put("status", 404);

            return ResponseEntity.status(404).body(data);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Paciente encontrado con éxito");
        data.put("status", 200);
        data.put("especialista", this.mapToEspecialistaResponse(especialista));

        return ResponseEntity.status(200).body(data);
    }


    private EspecialistaResponse mapToEspecialistaResponse(Especialista especialista){
        return EspecialistaResponse.builder()
                .idEspecialista(especialista.getIdEspecialista())
                .idUsuario(especialista.getUsuario().getIdUsuario())
                .usuario(especialista.getUsuario())
                .build();
    }

    public ResponseEntity<Object> insert(EspecialistaRequest especialistaRequest) {

        Especialista especialista = new Especialista();
        especialista.setUsuario(usuarioRepository.findById(especialistaRequest.getIdUsuario()).orElseThrow());

        especialistaRepository.save(especialista);

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Paciente creado con exito");
        data.put("status", 201);
        data.put("especialista", this.mapToEspecialistaResponse(especialista));

        return ResponseEntity.status(201).body(especialistaRequest);
    }
}
