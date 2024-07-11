package com.userservice.userservice.repository;

import com.userservice.userservice.model.Especialista;
import com.userservice.userservice.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialistaRepository extends JpaRepository<Especialista, Integer> {
    Optional<Especialista> findFirstByUsuarioIdUsuario(Integer id_usuario);
}
