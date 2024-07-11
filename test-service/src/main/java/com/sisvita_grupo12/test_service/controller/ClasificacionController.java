package com.sisvita_grupo12.test_service.controller;

import com.sisvita_grupo12.test_service.dto.ClasificacionRequest;
import com.sisvita_grupo12.test_service.dto.ClasificacionResponse;
import com.sisvita_grupo12.test_service.model.Clasificacion;
import com.sisvita_grupo12.test_service.service.ClasificacionService;
import com.sisvita_grupo12.test_service.service.TipoTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clasificaciones")
@RequiredArgsConstructor
public class ClasificacionController {

    private final ClasificacionService clasificacionService;
    private final TipoTestService tipoTestService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public ClasificacionResponse createClasificacion(@RequestBody ClasificacionRequest clasificacionRequest) {
        Clasificacion clasificacion = clasificacionService.createClasificacion(clasificacionRequest);
        ClasificacionResponse clasificacionResponse = new ClasificacionResponse();
        clasificacionResponse.setId_clasificacion(clasificacion.getId_clasificacion());
        clasificacionResponse.setMinimo(clasificacion.getMinimo());
        clasificacionResponse.setMaximo(clasificacion.getMaximo());
        clasificacionResponse.setInterpretacion(clasificacion.getInterpretacion());
        clasificacionResponse.setTipo_test(tipoTestService.getTipoTestById(clasificacion.getTipo_test().getId_tipo_test()));
        return clasificacionResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<ClasificacionResponse> getAllClasificaciones() {
        return clasificacionService.getAllClasificaciones();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClasificacionResponse getClasificacionById(@PathVariable Integer id) {
        return clasificacionService.getClasificacionById(id);
    }

}
