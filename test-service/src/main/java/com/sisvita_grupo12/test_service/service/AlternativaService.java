package com.sisvita_grupo12.test_service.service;

import com.sisvita_grupo12.test_service.dto.AlternativaRequest;
import com.sisvita_grupo12.test_service.dto.AlternativaResponse;
import com.sisvita_grupo12.test_service.model.Alternativa;
import com.sisvita_grupo12.test_service.repository.AlternativaRepository;
import com.sisvita_grupo12.test_service.dto.TipoTestResponse;
import com.sisvita_grupo12.test_service.repository.TipoTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlternativaService {

    private final AlternativaRepository alternativaRepository;
    private final TipoTestRepository tipoTestRepository;

    public Alternativa createAlternativa(AlternativaRequest alternativaRequest) {
        Alternativa alternativa = new Alternativa();
        alternativa.setContenido(alternativaRequest.getContenido());
        alternativa.setPuntaje(alternativaRequest.getPuntaje());
        alternativa.setTipo_test(tipoTestRepository.findById(alternativaRequest.getId_tipo_test()).orElseThrow());
        return alternativaRepository.save(alternativa);
    }

    public List<AlternativaResponse> getAllAlternativas() {
        List<Alternativa> alternativas = alternativaRepository.findAll();
        return alternativas.stream().map(this::mapToAlternativaResponse).toList();
    }

    private AlternativaResponse mapToAlternativaResponse(Alternativa alternativa) {
        return AlternativaResponse.builder()
                .id_alternativa(alternativa.getId_alternativa())
                .contenido(alternativa.getContenido())
                .puntaje(alternativa.getPuntaje())
                .tipo_test(TipoTestResponse.builder()
                                .id_tipo_test(alternativa.getTipo_test().getId_tipo_test())
                                .nombre(alternativa.getTipo_test().getNombre())
                                .autor(alternativa.getTipo_test().getAutor())
                                .descripcion(alternativa.getTipo_test().getDescripcion())
                                .build())
                .build();
    }

    public AlternativaResponse getAlternativaById(Integer id) {
        Alternativa alternativa = alternativaRepository.findById(id).orElseThrow();
        return mapToAlternativaResponse(alternativa);
    }

}
