package com.sisvita_grupo12.vigilance_service.controller;

import com.sisvita_grupo12.vigilance_service.dto.TratamientoRequest;
import com.sisvita_grupo12.vigilance_service.dto.TratamientoResponse;
import com.sisvita_grupo12.vigilance_service.model.Tratamiento;
import com.sisvita_grupo12.vigilance_service.service.TratamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tratamientos")
@RequiredArgsConstructor
public class TratamientoController {

    private final TratamientoService tratamientoService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public TratamientoResponse createTratamiento(@RequestBody TratamientoRequest tratamientoRequest) {
        Tratamiento tratamiento = tratamientoService.createTratamiento(tratamientoRequest);
        TratamientoResponse tratamientoResponse = new TratamientoResponse();
        tratamientoResponse.setId_tratamiento(tratamiento.getId_tratamiento());
        tratamientoResponse.setDescripcion(tratamiento.getDescripcion());
        tratamientoResponse.setFundamentacion(tratamiento.getFundamentacion());
        return tratamientoResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<TratamientoResponse> getTratamiento() {
        return tratamientoService.getAllTratamientos();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TratamientoResponse getTratamientoById(@PathVariable Integer id) {
        return tratamientoService.getTratamientoById(id);
    }
}
