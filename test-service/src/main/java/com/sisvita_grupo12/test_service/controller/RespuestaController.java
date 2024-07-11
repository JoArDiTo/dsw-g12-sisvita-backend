package com.sisvita_grupo12.test_service.controller;

import com.sisvita_grupo12.test_service.dto.RespuestaResponse;
import com.sisvita_grupo12.test_service.dto.RespuestaRequest;
import com.sisvita_grupo12.test_service.model.Respuesta;
import com.sisvita_grupo12.test_service.service.RespuestaService;

import com.sisvita_grupo12.test_service.service.TestService;
import com.sisvita_grupo12.test_service.service.PreguntaService;
import com.sisvita_grupo12.test_service.service.AlternativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService respuestaService;
    private final TestService testService;
    private final PreguntaService preguntaService;
    private final AlternativaService alternativaService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaResponse createRespuesta(@RequestBody RespuestaRequest respuestaRequest) {
        Respuesta respuesta = respuestaService.createRespuesta(respuestaRequest);
        RespuestaResponse respuestaResponse = new RespuestaResponse();
        respuesta.setId_respuesta(respuesta.getId_respuesta());
        respuestaResponse.setTest(testService.getTestById(respuesta.getTest().getId_test()));
        respuestaResponse.setPregunta(preguntaService.getPreguntaById(respuesta.getPregunta().getId_pregunta()));
        respuestaResponse.setAlternativa(alternativaService.getAlternativaById(respuesta.getAlternativa().getId_alternativa()));

        return respuestaResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<RespuestaResponse> getRespuestas() { return respuestaService.getAllRespuestas(); }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespuestaResponse getRespuestaById(@PathVariable Integer id) {
        return respuestaService.getRespuestaById(id);
    }

}
