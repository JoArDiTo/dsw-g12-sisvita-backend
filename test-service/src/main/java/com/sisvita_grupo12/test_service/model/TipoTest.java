package com.sisvita_grupo12.test_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tipo_test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo_test;
    private String nombre;
    private String autor;
    private String descripcion;

    @OneToMany(mappedBy = "tipo_test")
    private List<Pregunta> preguntas;

    @OneToMany(mappedBy = "tipo_test")
    private List<Clasificacion> clasificaciones;

    @OneToMany(mappedBy = "tipo_test")
    private List<Alternativa> alternativas;

    @OneToMany(mappedBy = "tipo_test")
    private List<Test> tests;
}
