package com.userservice.userservice.dto;

import com.userservice.userservice.model.Persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private String correo;
    private String password;
    private String documento;
}
