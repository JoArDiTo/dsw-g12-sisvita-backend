package com.sisvita_grupo12.test_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pregunta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pregunta;
    private String contenido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_test", referencedColumnName = "id_tipo_test")
    private TipoTest tipo_test;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;
}
