package com.sisvita_grupo12.test_service.controller;

import com.sisvita_grupo12.test_service.dto.TestRequest;
import com.sisvita_grupo12.test_service.dto.TestResponse;
import com.sisvita_grupo12.test_service.model.Test;
import com.sisvita_grupo12.test_service.service.TestService;
import com.sisvita_grupo12.test_service.service.TipoTestService;
import com.sisvita_grupo12.test_service.service.ClasificacionService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private final TestService testService;
    private final TipoTestService tipoTestService;
    private final ClasificacionService clasificacionService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public TestResponse createTest(@RequestBody TestRequest testRequest) {
        Test test = testService.createTest(testRequest);
        TestResponse testResponse = new TestResponse();
        testResponse.setId_test(test.getId_test());
        testResponse.setFecha(test.getFecha());
        testResponse.setResultado(test.getResultado());
        testResponse.setId_paciente(test.getIdPaciente());
        testResponse.setId_vigilancia(test.getId_vigilancia());
        testResponse.setTipo_test(tipoTestService.getTipoTestById(test.getTipo_test().getId_tipo_test()));
        testResponse.setClasificacion(clasificacionService.getClasificacionById(test.getClasificacion().getId_clasificacion()));
        return testResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<TestResponse> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TestResponse getTestById(@PathVariable Integer id) {
        return testService.getTestById(id);
    }

    @GetMapping("/getByPaciente/{id_paciente}")
    @ResponseStatus(HttpStatus.OK)
    public List<TestResponse> getTestByPaciente(@PathVariable Integer id_paciente) {
        return testService.getTestsByPaciente(id_paciente);
    }
}
