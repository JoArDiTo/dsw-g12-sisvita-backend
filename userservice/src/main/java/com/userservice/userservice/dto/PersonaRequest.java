package com.userservice.userservice.dto;

import com.userservice.userservice.model.Ubigeo;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaRequest {
    private String documento;
    private String tipo_documento;
    private String nombre;
    private String apellidoPaterno; // mapeo automatico de CamelCase a SnakeCase
    private String apellido_materno;
    private String telefono;
    private Date fecha_nacimiento;
    private String sexo;
    private String ubigeo;
}
