package com.sisvita_grupo12.test_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlternativaRequest {
    private String contenido;
    private Integer puntaje;
    private Integer id_tipo_test;
}
