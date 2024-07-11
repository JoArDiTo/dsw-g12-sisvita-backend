package com.sisvita_grupo12.test_service.service;

import com.sisvita_grupo12.test_service.dto.PreguntaRequest;
import com.sisvita_grupo12.test_service.dto.PreguntaResponse;
import com.sisvita_grupo12.test_service.model.Pregunta;
import com.sisvita_grupo12.test_service.repository.PreguntaRepository;
import com.sisvita_grupo12.test_service.dto.TipoTestResponse;
import com.sisvita_grupo12.test_service.repository.TipoTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreguntaService {

    private final PreguntaRepository preguntaRepository;
    private final TipoTestRepository tipoTestRepository;

    public Pregunta createPregunta(PreguntaRequest preguntaRequest) {
        Pregunta pregunta = new Pregunta();
        pregunta.setContenido(preguntaRequest.getContenido());
        pregunta.setTipo_test(tipoTestRepository.findById(preguntaRequest.getId_tipo_test()).orElseThrow());
        return preguntaRepository.save(pregunta);
    }

    public List<PreguntaResponse> getAllPreguntas() {
        List<Pregunta> preguntas = preguntaRepository.findAll();
        return preguntas.stream().map(this::mapToPreguntaResponse).toList();
    }

    private PreguntaResponse mapToPreguntaResponse(Pregunta pregunta) {
        return PreguntaResponse.builder()
                .id_pregunta(pregunta.getId_pregunta())
                .contenido(pregunta.getContenido())
                .tipo_test(TipoTestResponse.builder()
                        .id_tipo_test(pregunta.getTipo_test().getId_tipo_test())
                        .nombre(pregunta.getTipo_test().getNombre())
                        .autor(pregunta.getTipo_test().getAutor())
                        .descripcion(pregunta.getTipo_test().getDescripcion())
                        .build())
                .build();
    }

    public PreguntaResponse getPreguntaById(Integer id) {
        Pregunta pregunta = preguntaRepository.findById(id).orElseThrow();
        return mapToPreguntaResponse(pregunta);
    }

}
