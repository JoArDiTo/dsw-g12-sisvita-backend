package com.sisvita_grupo12.test_service.service;

import com.sisvita_grupo12.test_service.dto.TipoTestRequest;
import com.sisvita_grupo12.test_service.dto.TipoTestResponse;
import com.sisvita_grupo12.test_service.model.TipoTest;
import com.sisvita_grupo12.test_service.repository.TipoTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoTestService {

    private final TipoTestRepository tipoTestRepository;

    public TipoTest createTipoTest(TipoTestRequest tipoTestRequest) {
        TipoTest tipoTest = new TipoTest();
        tipoTest.setNombre(tipoTestRequest.getNombre());
        tipoTest.setAutor(tipoTestRequest.getAutor());
        tipoTest.setDescripcion(tipoTestRequest.getDescripcion());
        return tipoTestRepository.save(tipoTest);
    }

    public List<TipoTestResponse> getAllTipoTests() {
        List<TipoTest> tipoTests = tipoTestRepository.findAll();

        return tipoTests.stream().map(this::mapToTipoTestResponse).toList();
    }

    private TipoTestResponse mapToTipoTestResponse(TipoTest tipoTest) {
        return TipoTestResponse.builder()
                .id_tipo_test(tipoTest.getId_tipo_test())
                .nombre(tipoTest.getNombre())
                .autor(tipoTest.getAutor())
                .descripcion(tipoTest.getDescripcion())
                .build();
    }

    public TipoTestResponse getTipoTestById(Integer id) {
        TipoTest tipoTest = tipoTestRepository.findById(id).orElseThrow();
        return mapToTipoTestResponse(tipoTest);
    }

}
