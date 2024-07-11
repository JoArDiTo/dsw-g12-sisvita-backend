package com.sisvita_grupo12.vigilance_service.service;

import com.sisvita_grupo12.vigilance_service.dto.TratamientoRequest;
import com.sisvita_grupo12.vigilance_service.dto.TratamientoResponse;
import com.sisvita_grupo12.vigilance_service.model.Tratamiento;
import com.sisvita_grupo12.vigilance_service.repository.TratamientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TratamientoService {

    private final TratamientoRepository tratamientoRepository;

    public Tratamiento createTratamiento(TratamientoRequest tratamientoRequest) {
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setDescripcion(tratamientoRequest.getDescripcion());
        tratamiento.setFundamentacion(tratamientoRequest.getFundamentacion());
        return tratamientoRepository.save(tratamiento);
    }

    public List<TratamientoResponse> getAllTratamientos() {
        List<Tratamiento> tratamientos = tratamientoRepository.findAll();

        return tratamientos.stream().map(this::mapToTratamientoResponse).toList();
    }

    private TratamientoResponse mapToTratamientoResponse(Tratamiento tratamiento) {
        return TratamientoResponse.builder()
                .id_tratamiento(tratamiento.getId_tratamiento())
                .descripcion(tratamiento.getDescripcion())
                .fundamentacion(tratamiento.getFundamentacion())
                .build();
    }

    public TratamientoResponse getTratamientoById(Integer id) {
        Tratamiento tratamiento = tratamientoRepository.findById(id).orElseThrow();
        return mapToTratamientoResponse(tratamiento);
    }
}
