package com.sisvita_grupo12.test_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaRequest {
    private Integer id_test;
    private Integer id_pregunta;
    private Integer id_alternativa;
}
