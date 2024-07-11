package com.sisvita_grupo12.test_service.controller;

import com.sisvita_grupo12.test_service.dto.TipoTestRequest;
import com.sisvita_grupo12.test_service.dto.TipoTestResponse;
import com.sisvita_grupo12.test_service.model.TipoTest;
import com.sisvita_grupo12.test_service.service.TipoTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
@RequiredArgsConstructor
public class TipoTestController {
    private final TipoTestService tipoTestService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoTestResponse createTipoTest(@RequestBody TipoTestRequest tipoTestRequest) {
        TipoTest tipoTest = tipoTestService.createTipoTest(tipoTestRequest);
        TipoTestResponse tipoTestResponse = new TipoTestResponse();
        tipoTestResponse.setId_tipo_test(tipoTest.getId_tipo_test());
        tipoTestResponse.setNombre(tipoTest.getNombre());
        tipoTestResponse.setAutor(tipoTest.getAutor());
        tipoTestResponse.setDescripcion(tipoTest.getDescripcion());
        return tipoTestResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoTestResponse> getTipoTest() { return tipoTestService.getAllTipoTests(); }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoTestResponse getTipoTestById(@PathVariable Integer id) {
        return tipoTestService.getTipoTestById(id);
    }

}
