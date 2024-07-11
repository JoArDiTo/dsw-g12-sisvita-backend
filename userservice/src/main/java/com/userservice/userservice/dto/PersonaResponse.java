package com.userservice.userservice.dto;

import com.userservice.userservice.model.Ubigeo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaResponse {
    private String documento;
    private String tipo_documento;
    private String nombre;
    private String apellidoPaterno;
    private String apellido_materno;
    private String telefono;
    private Date fecha_nacimiento;
    private String sexo;
    private String id_ubigeo;
    private Ubigeo ubigeo;
}
