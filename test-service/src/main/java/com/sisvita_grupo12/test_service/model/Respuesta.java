package com.sisvita_grupo12.test_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "respuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_respuesta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_test", referencedColumnName = "id_test")
    private Test test;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    private Pregunta pregunta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_alternativa", referencedColumnName = "id_alternativa")
    private Alternativa alternativa;
}
