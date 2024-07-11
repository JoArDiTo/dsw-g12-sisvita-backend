package com.sisvita_grupo12.vigilance_service.dto;

import com.sisvita_grupo12.vigilance_service.model.Diagnostico;
import com.sisvita_grupo12.vigilance_service.model.Tratamiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VigilanciaResponse {
    private Integer id_vigilancia;
    private DiagnosticoResponse diagnostico;
    private TratamientoResponse tratamiento;
    private String observacion;
    private String fundamentacion;
}
