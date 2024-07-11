package com.sisvita_grupo12.test_service.dto;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestRequest {
    private Integer id_tipo_test;
    private Integer id_paciente;
    private Integer id_vigilancia;
    private Integer id_clasificacion;
    private Integer resultado;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
}
