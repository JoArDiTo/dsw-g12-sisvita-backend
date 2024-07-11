package com.sisvita_grupo12.vigilance_service.service;

import com.sisvita_grupo12.vigilance_service.dto.DiagnosticoRequest;
import com.sisvita_grupo12.vigilance_service.dto.DiagnosticoResponse;
import com.sisvita_grupo12.vigilance_service.model.Diagnostico;
import com.sisvita_grupo12.vigilance_service.repository.DiagnosticoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosticoService {

    private final DiagnosticoRepository diagnosticoRepository;
    public Diagnostico createDiagnostico(DiagnosticoRequest diagnosticoRequest) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setDescripcion(diagnosticoRequest.getDescripcion());
        diagnostico.setFundamentacion(diagnosticoRequest.getFundamentacion());
        return diagnosticoRepository.save(diagnostico);
    }

    public List<DiagnosticoResponse> getAllDiagnosticos() {
        List<Diagnostico> diagnosticos = diagnosticoRepository.findAll();

        return diagnosticos.stream().map(this::mapToDiagnosticoResponse).toList();
    }

    private DiagnosticoResponse mapToDiagnosticoResponse(Diagnostico diagnostico) {
        return DiagnosticoResponse.builder()
                .id_diagnostico(diagnostico.getId_diagnostico())
                .descripcion(diagnostico.getDescripcion())
                .fundamentacion(diagnostico.getFundamentacion())
                .build();
    }

    public DiagnosticoResponse getDiagnosticoById(Integer id) {
        Diagnostico diagnostico = diagnosticoRepository.findById(id).orElseThrow();
        return mapToDiagnosticoResponse(diagnostico);
    }
}
