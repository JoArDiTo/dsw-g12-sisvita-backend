package com.sisvita_grupo12.test_service.controller;

import com.sisvita_grupo12.test_service.dto.AlternativaRequest;
import com.sisvita_grupo12.test_service.dto.AlternativaResponse;
import com.sisvita_grupo12.test_service.model.Alternativa;
import com.sisvita_grupo12.test_service.service.AlternativaService;
import com.sisvita_grupo12.test_service.service.TipoTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alternativas")
@RequiredArgsConstructor
public class AlternativaController {

    private final AlternativaService alternativaService;
    private final TipoTestService tipoTestService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public AlternativaResponse createAlternativa(@RequestBody AlternativaRequest alternativaRequest) {
        Alternativa alternativa = alternativaService.createAlternativa(alternativaRequest);
        AlternativaResponse alternativaResponse = new AlternativaResponse();
        alternativaResponse.setContenido(alternativa.getContenido());
        alternativaResponse.setPuntaje(alternativa.getPuntaje());
        alternativaResponse.setTipo_test(tipoTestService.getTipoTestById(alternativa.getTipo_test().getId_tipo_test()));
        return alternativaResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<AlternativaResponse> getAllAlternativas() {
        return alternativaService.getAllAlternativas();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlternativaResponse getAlternativaById(@PathVariable Integer id) {
        return alternativaService.getAlternativaById(id);
    }

}
