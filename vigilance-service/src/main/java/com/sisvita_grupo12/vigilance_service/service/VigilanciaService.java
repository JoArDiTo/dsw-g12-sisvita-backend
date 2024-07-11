package com.sisvita_grupo12.vigilance_service.service;

import com.sisvita_grupo12.vigilance_service.dto.DiagnosticoResponse;
import com.sisvita_grupo12.vigilance_service.dto.TratamientoResponse;
import com.sisvita_grupo12.vigilance_service.dto.VigilanciaRequest;
import com.sisvita_grupo12.vigilance_service.dto.VigilanciaResponse;
import com.sisvita_grupo12.vigilance_service.model.Diagnostico;
import com.sisvita_grupo12.vigilance_service.model.Vigilancia;
import com.sisvita_grupo12.vigilance_service.repository.DiagnosticoRepository;
import com.sisvita_grupo12.vigilance_service.repository.TratamientoRepository;
import com.sisvita_grupo12.vigilance_service.repository.VigilanciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VigilanciaService {

    private final VigilanciaRepository vigilanciaRespository;
    private final DiagnosticoRepository diagnosticoRepository;
    private final TratamientoRepository tratamientoRespository;

    public Vigilancia createVigilancia(VigilanciaRequest vigilanciaRequest) {
        Vigilancia vigilancia = new Vigilancia();
        vigilancia.setId_vigilancia(UUID.randomUUID().hashCode());
        vigilancia.setDiagnostico(diagnosticoRepository.findById(vigilanciaRequest.getId_diagnostico()).orElseThrow());
        vigilancia.setTratamiento(tratamientoRespository.findById(vigilanciaRequest.getId_tratamiento()).orElseThrow());
        vigilancia.setObservacion(vigilanciaRequest.getObservacion());
        vigilancia.setFundamentacion(vigilanciaRequest.getFundamentacion());

        return vigilanciaRespository.save(vigilancia);
    }

    public List<VigilanciaResponse> getAllVigilancias() {
        List<Vigilancia> vigilancias = vigilanciaRespository.findAll();
        return vigilancias.stream().map(this::mapToVigilanciaResponse).toList();
    }

    private VigilanciaResponse mapToVigilanciaResponse(Vigilancia vigilancia) {
        return VigilanciaResponse.builder()
                .id_vigilancia(vigilancia.getId_vigilancia())
                .diagnostico(DiagnosticoResponse.builder()
                        .id_diagnostico(vigilancia.getDiagnostico().getId_diagnostico())
                        .descripcion(vigilancia.getDiagnostico().getDescripcion())
                        .fundamentacion(vigilancia.getDiagnostico().getFundamentacion())
                        .build())
                .tratamiento(TratamientoResponse.builder()
                        .id_tratamiento(vigilancia.getTratamiento().getId_tratamiento())
                        .descripcion(vigilancia.getTratamiento().getDescripcion())
                        .fundamentacion(vigilancia.getTratamiento().getFundamentacion())
                        .build())
                .observacion(vigilancia.getObservacion())
                .fundamentacion(vigilancia.getFundamentacion())
                .build();
    }

    public VigilanciaResponse getVigilanciaById(Integer id) {
        Vigilancia vigilancia = vigilanciaRespository.findById(id).orElseThrow();
        return mapToVigilanciaResponse(vigilancia);
    }
}
