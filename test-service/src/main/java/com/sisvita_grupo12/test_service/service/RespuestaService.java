package com.sisvita_grupo12.test_service.service;

import com.sisvita_grupo12.test_service.dto.RespuestaRequest;
import com.sisvita_grupo12.test_service.dto.RespuestaResponse;
import com.sisvita_grupo12.test_service.model.Respuesta;
import com.sisvita_grupo12.test_service.repository.RespuestaRepository;

import com.sisvita_grupo12.test_service.dto.TestResponse;
import com.sisvita_grupo12.test_service.repository.TestRepository;
import com.sisvita_grupo12.test_service.dto.PreguntaResponse;
import com.sisvita_grupo12.test_service.repository.PreguntaRepository;
import com.sisvita_grupo12.test_service.dto.AlternativaResponse;
import com.sisvita_grupo12.test_service.repository.AlternativaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TestRepository testRepository;
    private final PreguntaRepository preguntaRepository;
    private final AlternativaRepository alternativaRepository;

    public Respuesta createRespuesta(RespuestaRequest respuestaRequest) {
        Respuesta respuesta = new Respuesta();
        respuesta.setTest(testRepository.findById(respuestaRequest.getId_test()).orElseThrow());
        respuesta.setPregunta(preguntaRepository.findById(respuestaRequest.getId_pregunta()).orElseThrow());
        respuesta.setAlternativa(alternativaRepository.findById(respuestaRequest.getId_alternativa()).orElseThrow());
        return respuestaRepository.save(respuesta);
    }

    public List<RespuestaResponse> getAllRespuestas() {
        List<Respuesta> respuestas = respuestaRepository.findAll();
        return respuestas.stream().map(this::mapToRespuestaResponse).toList();
    }

    private RespuestaResponse mapToRespuestaResponse(Respuesta respuesta) {
        return RespuestaResponse.builder()
                .id_respuesta(respuesta.getId_respuesta())
                .id_test(respuesta.getTest().getId_test())
                .id_pregunta(respuesta.getPregunta().getId_pregunta())
                .id_alternativa(respuesta.getAlternativa().getId_alternativa())
                .test(TestResponse.builder()
                        .id_test(respuesta.getTest().getId_test())
                        .build())
                .pregunta(PreguntaResponse.builder()
                        .id_pregunta(respuesta.getPregunta().getId_pregunta())
                        .build())
                .alternativa(AlternativaResponse.builder()
                        .id_alternativa(respuesta.getAlternativa().getId_alternativa())
                        .build())
                .build();
    }

    public RespuestaResponse getRespuestaById(Integer id){
        Respuesta respuesta = respuestaRepository.findById(id).orElseThrow();
        return mapToRespuestaResponse(respuesta);
    }

}
