package com.sisvita_grupo12.test_service.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestResponse {
    private Integer id_test;
    private TipoTestResponse tipo_test;
    private Integer id_paciente;
    private Integer id_vigilancia;
    private ClasificacionResponse clasificacion;
    private Integer resultado;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
}
