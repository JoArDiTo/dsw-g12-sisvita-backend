package com.sisvita_grupo12.vigilance_service.controller;

import com.sisvita_grupo12.vigilance_service.dto.DiagnosticoRequest;
import com.sisvita_grupo12.vigilance_service.dto.DiagnosticoResponse;
import com.sisvita_grupo12.vigilance_service.model.Diagnostico;
import com.sisvita_grupo12.vigilance_service.service.DiagnosticoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnosticos")
@RequiredArgsConstructor
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public DiagnosticoResponse createDiagnostico(@RequestBody DiagnosticoRequest diagnosticoRequest) {
        Diagnostico diagnostico = diagnosticoService.createDiagnostico(diagnosticoRequest);
        DiagnosticoResponse diagnosticoResponse = new DiagnosticoResponse();
        diagnosticoResponse.setId_diagnostico(diagnostico.getId_diagnostico());
        diagnosticoResponse.setDescripcion(diagnostico.getDescripcion());
        diagnosticoResponse.setFundamentacion(diagnostico.getFundamentacion());
        return diagnosticoResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<DiagnosticoResponse> getDiagnostico() {
        return diagnosticoService.getAllDiagnosticos();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DiagnosticoResponse getDiagnosticoById(@PathVariable Integer id) {
        return diagnosticoService.getDiagnosticoById(id);
    }
}
