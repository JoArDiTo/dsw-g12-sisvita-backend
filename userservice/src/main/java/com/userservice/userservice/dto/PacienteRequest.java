package com.userservice.userservice.dto;

import com.userservice.userservice.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {
    private int idUsuario;
}
