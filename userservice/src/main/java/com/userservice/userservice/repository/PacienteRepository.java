package com.userservice.userservice.repository;

import com.userservice.userservice.model.Paciente;
import com.userservice.userservice.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Optional<Paciente> findFirstByUsuarioIdUsuario(Integer id_usuario);
}
