package com.sisvita_grupo12.vigilance_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VigilanciaRequest {
    private Integer id_diagnostico;
    private Integer id_tratamiento;
    private String observacion;
    private String fundamentacion;
}
