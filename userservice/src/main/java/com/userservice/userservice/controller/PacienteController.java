package com.userservice.userservice.controller;

import com.userservice.userservice.dto.PacienteRequest;
import com.userservice.userservice.dto.PacienteResponse;
import com.userservice.userservice.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteResponse> get_pacientes(){
        return pacienteService.getPacientes();
    }

    @GetMapping("/get/{id_usuario}")
    public ResponseEntity<Object> get_paciente(@PathVariable Integer id_usuario) {
        return pacienteService.getPaciente(id_usuario);
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insertPersona(@RequestBody PacienteRequest pacienteRequest) {
        return  pacienteService.insert(pacienteRequest);
    }
}
