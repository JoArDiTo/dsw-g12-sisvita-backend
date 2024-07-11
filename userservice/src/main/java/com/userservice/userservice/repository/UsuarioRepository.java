package com.userservice.userservice.repository;

import com.userservice.userservice.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);

    Optional<Usuario> findFirstByPersonaDocumento(String documento);
}
