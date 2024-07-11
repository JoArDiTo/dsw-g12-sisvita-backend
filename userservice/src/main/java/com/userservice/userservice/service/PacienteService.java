package com.userservice.userservice.service;

import com.userservice.userservice.dto.PacienteRequest;
import com.userservice.userservice.dto.PacienteResponse;
import com.userservice.userservice.dto.UbigeoResponse;
import com.userservice.userservice.model.Paciente;
import com.userservice.userservice.model.Ubigeo;
import com.userservice.userservice.repository.PacienteRepository;
import com.userservice.userservice.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;

    public List<PacienteResponse> getPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(this::mapToPacienteResponse).toList();
    }

    public ResponseEntity<Object> getPaciente(Integer id_usuario) {
        Paciente paciente = pacienteRepository.findById(id_usuario).orElse(null);

        if (paciente == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "Paciente no encontrado");
            data.put("status", 404);

            return ResponseEntity.status(404).body(data);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Paciente encontrado con éxito");
        data.put("status", 200);
        data.put("paciente", this.mapToPacienteResponse(paciente));

        return ResponseEntity.status(200).body(data);
    }


    private PacienteResponse mapToPacienteResponse(Paciente paciente){
        return PacienteResponse.builder()
                .idPaciente(paciente.getIdPaciente())
                .id_usuario(paciente.getUsuario().getIdUsuario())
                .usuario(paciente.getUsuario())
                .build();
    }

    public ResponseEntity<Object> insert(PacienteRequest pacienteRequest) {
        Paciente paciente = new Paciente();
        paciente.setUsuario(usuarioRepository.findById(pacienteRequest.getIdUsuario()).orElseThrow());

        pacienteRepository.save(paciente);

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Paciente creado con exito");
        data.put("status", 201);
        data.put("paciente", this.mapToPacienteResponse(paciente));

        return ResponseEntity.status(201).body(data);
    }
}
