/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.userservice.userservice.controller;

import com.userservice.userservice.dto.EspecialistaRequest;
import com.userservice.userservice.dto.EspecialistaResponse;
import com.userservice.userservice.service.EspecialistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/especialistas")
@RequiredArgsConstructor
public class EspecialistaController {

    private final EspecialistaService especialistaService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<EspecialistaResponse> get_especialistas(){
        return especialistaService.getEspecialistas();
    }

    @GetMapping("/get/{id_usuario}")
    public ResponseEntity<Object> get_paciente(@PathVariable Integer id_usuario) {
        return especialistaService.getEspecialista(id_usuario);
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insert(@RequestBody EspecialistaRequest especialistaRequest) {
        return  especialistaService.insert(especialistaRequest);
    }
}
