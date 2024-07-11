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
public class EspecialistaResponse {
    private int idEspecialista;
    private int idUsuario;
    private String licencia;
    private String especialidad;

    private Usuario usuario;
}
