package com.sisvita_grupo12.test_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClasificacionResponse {
    private Integer id_clasificacion;
    private Integer minimo;
    private Integer maximo;
    private String interpretacion;
    private TipoTestResponse tipo_test;
}
