package com.sisvita_grupo12.test_service.controller;

import com.sisvita_grupo12.test_service.dto.PreguntaRequest;
import com.sisvita_grupo12.test_service.dto.PreguntaResponse;
import com.sisvita_grupo12.test_service.model.Pregunta;
import com.sisvita_grupo12.test_service.service.PreguntaService;
import com.sisvita_grupo12.test_service.service.TipoTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preguntas")
@RequiredArgsConstructor
public class PreguntaController {

    private final PreguntaService preguntaService;
    private final TipoTestService tipoTestService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public PreguntaResponse createPregunta(@RequestBody PreguntaRequest preguntaRequest) {
        Pregunta pregunta = preguntaService.createPregunta(preguntaRequest);
        PreguntaResponse preguntaResponse = new PreguntaResponse();
        preguntaResponse.setContenido(pregunta.getContenido());
        preguntaResponse.setTipo_test(tipoTestService.getTipoTestById(pregunta.getTipo_test().getId_tipo_test()));
        return preguntaResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<PreguntaResponse> getAllPreguntas() {
        return preguntaService.getAllPreguntas();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PreguntaResponse getPreguntaById(@PathVariable Integer id) {
        return preguntaService.getPreguntaById(id);
    }

}
