package com.userservice.userservice.controller;

import com.userservice.userservice.dto.PersonaRequest;
import com.userservice.userservice.dto.PersonaResponse;
import com.userservice.userservice.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaService personaService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonaResponse> get_personas(){
        return personaService.getAllPersonas();
    }
    @GetMapping("/get/{documento}")
    public ResponseEntity<Object>getPersona(@PathVariable String documento) {
        return personaService.get_persona(documento);
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insertPersona(@RequestBody PersonaRequest personaRequest) {
        return personaService.insertPersona(personaRequest);
    }

    }
