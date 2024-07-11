package com.userservice.userservice.repository;

import com.userservice.userservice.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, String> {
}
