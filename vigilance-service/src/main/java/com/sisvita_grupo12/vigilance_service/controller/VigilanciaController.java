package com.sisvita_grupo12.vigilance_service.controller;

import com.sisvita_grupo12.vigilance_service.dto.VigilanciaRequest;
import com.sisvita_grupo12.vigilance_service.dto.VigilanciaResponse;
import com.sisvita_grupo12.vigilance_service.model.Vigilancia;
import com.sisvita_grupo12.vigilance_service.service.DiagnosticoService;
import com.sisvita_grupo12.vigilance_service.service.TratamientoService;
import com.sisvita_grupo12.vigilance_service.service.VigilanciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vigilancias")
@RequiredArgsConstructor
public class VigilanciaController {

    private final VigilanciaService vigilanciaService;
    private final DiagnosticoService diagnosticoService;
    private final TratamientoService tratamientoService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public VigilanciaResponse createVigilancia(@RequestBody VigilanciaRequest vigilanciaRequest) {
        Vigilancia vigilancia = vigilanciaService.createVigilancia(vigilanciaRequest);
        VigilanciaResponse vigilanciaResponse = new VigilanciaResponse();
        vigilanciaResponse.setId_vigilancia(vigilancia.getId_vigilancia());
        vigilanciaResponse.setDiagnostico(diagnosticoService.getDiagnosticoById(vigilancia.getDiagnostico().getId_diagnostico()));
        vigilanciaResponse.setTratamiento(tratamientoService.getTratamientoById(vigilancia.getTratamiento().getId_tratamiento()));
        vigilanciaResponse.setObservacion(vigilancia.getObservacion());
        vigilanciaResponse.setFundamentacion(vigilancia.getFundamentacion());
        return vigilanciaResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<VigilanciaResponse> getAllVigilancias() {
        return vigilanciaService.getAllVigilancias();
    }

    @GetMapping("get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VigilanciaResponse getVigilanciaById(@PathVariable Integer id) {
        return vigilanciaService.getVigilanciaById(id);
    }
}
