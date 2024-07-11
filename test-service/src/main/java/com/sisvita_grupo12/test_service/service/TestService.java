package com.sisvita_grupo12.test_service.service;

import com.sisvita_grupo12.test_service.dto.TestRequest;
import com.sisvita_grupo12.test_service.dto.TestResponse;
import com.sisvita_grupo12.test_service.model.Test;
import com.sisvita_grupo12.test_service.repository.TestRepository;
import com.sisvita_grupo12.test_service.dto.TipoTestResponse;
import com.sisvita_grupo12.test_service.repository.TipoTestRepository;
import com.sisvita_grupo12.test_service.dto.ClasificacionResponse;
import com.sisvita_grupo12.test_service.repository.ClasificacionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    @Autowired
    private final TestRepository testRepository;
    private final TipoTestRepository tipoTestRepository;
    private final ClasificacionRepository clasificacionRepository;

    public Test createTest(TestRequest testRequest) {
        Test test = new Test();
        test.setResultado(testRequest.getResultado());
        test.setFecha(testRequest.getFecha());
        test.setTipo_test(tipoTestRepository.findById(testRequest.getId_tipo_test()).orElseThrow());
        test.setClasificacion(clasificacionRepository.findById(testRequest.getId_clasificacion()).orElseThrow());
        test.setIdPaciente(testRequest.getId_paciente());
        test.setId_vigilancia(testRequest.getId_vigilancia());
        return testRepository.save(test);
    }

    public List<TestResponse> getAllTests() {
        List<Test> tests = testRepository.findAll();
        return tests.stream().map(this::mapToTestResponse).toList();
    }

    private TestResponse mapToTestResponse(Test test){
        return TestResponse.builder()
                .id_test(test.getId_test())
                .resultado(test.getResultado())
                .fecha(test.getFecha())
                .tipo_test(TipoTestResponse.builder()
                        .id_tipo_test(test.getTipo_test().getId_tipo_test())
                        .nombre(test.getTipo_test().getNombre())
                        .autor(test.getTipo_test().getAutor())
                        .descripcion(test.getTipo_test().getDescripcion())
                        .build())
                .clasificacion(ClasificacionResponse.builder()
                        .id_clasificacion(test.getClasificacion().getId_clasificacion())
                        .minimo(test.getClasificacion().getMinimo())
                        .maximo(test.getClasificacion().getMaximo())
                        .interpretacion(test.getClasificacion().getInterpretacion())
                        .tipo_test(TipoTestResponse.builder()
                                .id_tipo_test(test.getClasificacion().getTipo_test().getId_tipo_test())
                                .nombre(test.getClasificacion().getTipo_test().getNombre())
                                .autor(test.getClasificacion().getTipo_test().getAutor())
                                .descripcion(test.getClasificacion().getTipo_test().getDescripcion())
                                .build())
                        .build())
                .id_paciente(test.getIdPaciente())
                .id_vigilancia(test.getId_vigilancia())
                .build();
    }

    public TestResponse getTestById(Integer id) {
        Test test = testRepository.findById(id).orElseThrow();
        return mapToTestResponse(test);
    }

    public List<TestResponse> getTestsByPaciente(Integer id) {
        List<Test> tests = testRepository.findByIdPaciente(id);
        return tests.stream().map(this::mapToTestResponse).toList();
    }
}
