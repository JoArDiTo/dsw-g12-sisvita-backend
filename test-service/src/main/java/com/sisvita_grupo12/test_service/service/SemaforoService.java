package com.sisvita_grupo12.test_service.service;

import com.sisvita_grupo12.test_service.dto.SemaforoRequest;
import com.sisvita_grupo12.test_service.dto.SemaforoResponse;
import com.sisvita_grupo12.test_service.model.Semaforo;
import com.sisvita_grupo12.test_service.repository.SemaforoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemaforoService {

    private final SemaforoRepository semaforoRepository;

    public Semaforo createSemaforo(SemaforoRequest semaforoRequest) {
        Semaforo semaforo = new Semaforo();
        semaforo.setColor(semaforoRequest.getColor());
        return semaforoRepository.save(semaforo);
    }

    public List<SemaforoResponse> getAllSemaforos() {
        List<Semaforo> semaforos = semaforoRepository.findAll();

        return semaforos.stream().map(this::mapToSemaforoResponse).toList();
    }

    private SemaforoResponse mapToSemaforoResponse(Semaforo semaforo) {
        return SemaforoResponse.builder()
                .id_semaforo(semaforo.getId_semaforo())
                .color(semaforo.getColor())
                .build();
    }

    public SemaforoResponse getSemaforoById(Integer id){
        Semaforo semaforo = semaforoRepository.findById(id).orElseThrow();
        return mapToSemaforoResponse(semaforo);
    }
}
