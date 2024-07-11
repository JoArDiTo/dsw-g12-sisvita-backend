package com.sisvita_grupo12.test_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoTestResponse {
    private Integer id_tipo_test;
    private String nombre;
    private String autor;
    private String descripcion;
}
