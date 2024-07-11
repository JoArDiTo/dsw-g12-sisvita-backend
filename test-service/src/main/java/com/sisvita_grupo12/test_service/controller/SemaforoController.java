package com.sisvita_grupo12.test_service.controller;

import com.sisvita_grupo12.test_service.dto.SemaforoRequest;
import com.sisvita_grupo12.test_service.dto.SemaforoResponse;
import com.sisvita_grupo12.test_service.model.Semaforo;
import com.sisvita_grupo12.test_service.service.SemaforoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semaforos")
@RequiredArgsConstructor
public class SemaforoController {
    private final SemaforoService semaforoService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public SemaforoResponse createSemaforo(@RequestBody SemaforoRequest semaforoRequest) {
        Semaforo semaforo = semaforoService.createSemaforo(semaforoRequest);
        SemaforoResponse semaforoResponse = new SemaforoResponse();
        semaforoResponse.setId_semaforo(semaforo.getId_semaforo());
        semaforoResponse.setColor(semaforo.getColor());
        return semaforoResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<SemaforoResponse> getSemaforos() { return semaforoService.getAllSemaforos(); }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SemaforoResponse getSemaforoById(@PathVariable Integer id) {
        return semaforoService.getSemaforoById(id);
    }
}
