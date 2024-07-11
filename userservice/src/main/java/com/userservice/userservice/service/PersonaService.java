package com.userservice.userservice.service;

import com.userservice.userservice.dto.PersonaRequest;
import com.userservice.userservice.dto.PersonaResponse;
import com.userservice.userservice.dto.UbigeoResponse;
import com.userservice.userservice.model.Persona;
import com.userservice.userservice.model.Ubigeo;
import com.userservice.userservice.repository.PersonaRepository;
import com.userservice.userservice.repository.UbigeoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PersonaService {  //Missing : UPDATE , DELETE

    private final PersonaRepository personaRepository ;
    private final UbigeoRepository ubigeoRepository;
    public List<PersonaResponse> getAllPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return personas.stream().map(this::mapToPersonaResponse).toList();
    }

    public ResponseEntity<Object> insertPersona(PersonaRequest personaRequest) {
        String documento = personaRequest.getDocumento();

        // Verificar si la persona ya está registrada
        if (personaRepository.existsById(documento)) {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "Datos de persona ya registrada");
            data.put("status", 400);

            return ResponseEntity.status(400).body(data);
        }

        // Verificar si faltan datos
        if (personaRequest.getTipo_documento() == null || personaRequest.getNombre() == null || personaRequest.getApellidoPaterno() == null || personaRequest.getApellido_materno() == null || personaRequest.getTelefono() == null || personaRequest.getFecha_nacimiento() == null || personaRequest.getSexo() == null || personaRequest.getUbigeo()== null) {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "Faltan datos");
            data.put("status", 400);
            return ResponseEntity.status(400).body(data);
        }

        // Crear nueva persona
        Persona persona = new Persona();
        persona.setDocumento(personaRequest.getDocumento());
        persona.setTipo_documento(personaRequest.getTipo_documento());
        persona.setNombre(personaRequest.getNombre());
        persona.setApellido_materno(personaRequest.getApellido_materno());
        persona.setApellidoPaterno(personaRequest.getApellidoPaterno());
        persona.setTelefono(personaRequest.getTelefono());
        persona.setFecha_nacimiento(personaRequest.getFecha_nacimiento());
        persona.setSexo(personaRequest.getSexo());
        persona.setUbigeo(ubigeoRepository.findById(personaRequest.getUbigeo()).orElseThrow());

        personaRepository.save(persona);

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Persona creada con éxito");
        data.put("status",200);
        data.put("persona", this.mapToPersonaResponse(persona)); // Aquí puedes usar un DTO si es necesario

        return ResponseEntity.status(200).body(data);
    }

    public ResponseEntity<Object> get_persona(String documento) {
        Persona persona = personaRepository.findById(documento).orElse(null);

        if (persona == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "Persona no encontrada");
            data.put("status", 400);
            return ResponseEntity.status(400).body(data);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Persona encontrada con éxito");
        data.put("status", 200);
        data.put("persona", this.mapToPersonaResponse(persona));

        return ResponseEntity.status(200).body(data);
    }

    private PersonaResponse mapToPersonaResponse(Persona persona){
        return PersonaResponse.builder()
                .documento(persona.getDocumento())
                .tipo_documento(persona.getTipo_documento())
                .nombre(persona.getNombre())
                .apellidoPaterno(persona.getApellidoPaterno())
                .apellido_materno(persona.getApellido_materno())
                .telefono(persona.getTelefono())
                .fecha_nacimiento(persona.getFecha_nacimiento())
                .sexo(persona.getSexo())
                .id_ubigeo(persona.getUbigeo().getIdUbigeo())
                .ubigeo(persona.getUbigeo())
                .build();
    }
}
