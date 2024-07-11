package com.sisvita_grupo12.vigilance_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticoResponse {
    private Integer id_diagnostico;
    private String descripcion;
    private String fundamentacion;
}
