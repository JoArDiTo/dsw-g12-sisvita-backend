package com.sisvita_grupo12.test_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaResponse {
    private Integer id_pregunta;
    private String contenido;
    private TipoTestResponse tipo_test;
}
