package com.userservice.userservice.dto;

import com.userservice.userservice.model.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {
    private int idPaciente;
    private int id_usuario;
    private Usuario usuario;
}
